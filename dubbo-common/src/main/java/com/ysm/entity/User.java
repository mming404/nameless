package com.ysm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/24
 * @Version V1.0
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String username;
    private String password;
}
