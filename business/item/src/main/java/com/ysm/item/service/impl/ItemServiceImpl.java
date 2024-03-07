package com.ysm.item.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.common.core.utils.bean.BeanUtils;
import com.ysm.common.minio.MinioUtil;
import com.ysm.common.redis.service.RedisService;
import com.ysm.interaction.api.FollowServiceIRPC;
import com.ysm.item.api.ItemServiceIRPC;
import com.ysm.item.dto.ItemDTO;
import com.ysm.item.po.Item;
import com.ysm.item.service.ItemService;
import com.ysm.item.mapper.ItemMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author 86139
 * @description 针对表【item】的数据库操作Service实现
 * @createDate 2024-02-26 17:34:23
 */
@Service
@DubboService
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item>
        implements ItemServiceIRPC, ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private RedisService redisService;

    @DubboReference
    private FollowServiceIRPC followServiceIRPC;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public void postItem(MultipartFile file, Item itemBo) throws Exception {
        String filePath = minioUtil.uploadFile(file, file.getOriginalFilename(), file.getContentType());
        Assert.notNull(filePath, "上传失败");
        System.out.println(itemBo.toString());
        itemBo.setVideoUrl(filePath);
        Assert.isTrue(itemMapper.insert(itemBo) > 0, "上传成功");
        // TODO: 2024/3/1 判断时候是大v 是则写入自己的发件箱，不是则获取粉丝列表写入收件箱
        // TODO: 2024/3/2 去计数服务获取粉丝数判断
        Long followerCount = followServiceIRPC.getFollowerCount(itemBo.getUserId());
        // TODO: 2024/3/2 大v 直接写入自己发件箱
        if (followerCount > 1000L) {
            RedisTemplate redisTemplate = redisService.redisTemplate;
            // TODO: 2024/3/2 这里key结构的设计有待考量 考虑到数据倾斜问题
            redisTemplate.opsForZSet().add("feed:outbox:" + itemBo.getUserId().toString(),
                    itemBo.getId(),
                    (double) itemBo.getCreatedAt().toEpochSecond(ZoneOffset.of("+8")));
        } else {
            // TODO: 2024/3/2 不是大v rpc关系服务获取粉丝列表
            List<Long> fansIds = followServiceIRPC.listFansId(itemBo.getUserId());
            // TODO: 2024/3/2 走mq异步自发自收
            CompletableFuture.runAsync(() -> fansIds.forEach(fansId -> {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("fanId", fansId.toString());
                hashMap.put("itemId", itemBo.getId().toString());
                hashMap.put("score", Long.valueOf(itemBo.getCreatedAt().toEpochSecond(ZoneOffset.of("+8"))).toString());
                kafkaTemplate.send("user_inbox", JSON.toJSONString(hashMap));
            }));
        }
    }

    @Override
    public List<Item> listItem(String targetId) {
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Item::getUserId, targetId)
                .orderByDesc(Item::getCreatedAt);
        return list(wrapper);
    }

    @Override
    public ItemDTO getItem(Long id){
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyBeanProp(itemDTO,getById(id));
        return itemDTO;
    }
}




