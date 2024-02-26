package com.ysm.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/8
 * @Version V1.0
 **/
@Data
public class UserDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     * 唯一索引
     */
    private String username;


    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     * 唯一索引
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别 (0表示未知，1表示男，2表示女)
     */
    private Byte gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 用户状态: 1正常.
     */
    private Byte userStatus;

    /**
     * 创建时间 自动填充
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间 自动填充
     */
    private LocalDateTime updatedAt;

}
