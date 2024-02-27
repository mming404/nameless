package com.ysm.item.controller;

import com.alibaba.fastjson2.JSON;
import com.ysm.common.core.domain.CommonResult;
import com.ysm.common.minio.MinioUtil;
import com.ysm.item.po.Item;
import com.ysm.item.service.ItemService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/25
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private ItemService itemService;


    @PostMapping(value = "/item")
    public CommonResult<String> item(@RequestPart("file") MultipartFile file, @RequestPart("itemBo") String itemBo) throws Exception {
        itemService.postItem(file,JSON.parseObject(itemBo,Item.class));
        return CommonResult.ok("上传成功");
    }

    @PostMapping("/upload")
    public CommonResult<String> uploadFileTest(@RequestParam("file") MultipartFile file) throws Exception {
        String filePath = minioUtil.uploadFile(file, file.getOriginalFilename(), file.getContentType());
        return CommonResult.ok(filePath);
    }

    @GetMapping("/download")
    public CommonResult<String> downloadFileTest(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        return minioUtil.downloadFile(fileName, response) ?
                CommonResult.ok("下载成功") : CommonResult.fail("下载失败");
    }

    @GetMapping("/listItem/{targetId}")
    public CommonResult<List<Item>> listItem(@PathVariable String targetId){
        return CommonResult.ok(itemService.listItem(targetId));
    }


}
