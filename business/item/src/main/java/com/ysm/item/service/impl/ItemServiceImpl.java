package com.ysm.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.common.minio.MinioUtil;
import com.ysm.item.po.Item;
import com.ysm.item.service.ItemService;
import com.ysm.item.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 86139
 * @description 针对表【item】的数据库操作Service实现
 * @createDate 2024-02-26 17:34:23
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item>
        implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private MinioUtil minioUtil;

    @Override
    public void postItem(MultipartFile file, Item itemBo) throws Exception {
        String filePath = minioUtil.uploadFile(file, file.getOriginalFilename(), file.getContentType());
        Assert.notNull(filePath, "上传失败");
        System.out.println(itemBo.toString());
        itemBo.setVideoUrl(filePath);
        Assert.isTrue(itemMapper.insert(itemBo) > 0, "上传成功");
    }
}




