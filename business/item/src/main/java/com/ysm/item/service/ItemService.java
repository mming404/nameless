package com.ysm.item.service;

import com.ysm.item.po.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 86139
* @description 针对表【item】的数据库操作Service
* @createDate 2024-02-26 17:34:23
*/
public interface ItemService extends IService<Item> {


    /**
     * 用户上传item
     * @param file 文件
     * @param itemBo
     */
    void postItem(MultipartFile file, Item itemBo) throws Exception;
}
