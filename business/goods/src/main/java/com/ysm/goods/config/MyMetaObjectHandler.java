package com.ysm.goods.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 需要注册到Spring容器中
 *
 * @author ysm
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入时自动填充
        // 注意第二个参数要填写实体类中的字段名称，而不是表的列名称
        strictFillStrategy(metaObject, "createdAt", LocalDateTime::now);
        setFieldValByName("status", (byte) 1, metaObject);
        setFieldValByName("deleted", (byte) 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动填充
        strictFillStrategy(metaObject, "updatedAt", LocalDateTime::now);
    }
}