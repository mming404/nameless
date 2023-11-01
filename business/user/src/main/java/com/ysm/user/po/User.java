package com.ysm.user.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

/**
 * 
 * @author ysm
 * @TableName user
 */
@TableName(value ="user")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     * 唯一索引
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 手机号
     * 唯一索引
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 性别 (0表示未知，1表示男，2表示女)
     */
    @TableField(value = "gender")
    private Byte gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDate birthday;

    /**
     * 用户状态: 1正常.
     */
    @TableField(value = "user_status",fill = FieldFill.INSERT)
    private Byte userStatus;

    /**
     * 创建时间 自动填充
     */
    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间 自动填充
     */
    @TableField(value = "updated_at",fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除
     */
//    @TableField(value = "deleted",select = false)
    @TableLogic(value = "0", delval = "1")
    private Byte deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}