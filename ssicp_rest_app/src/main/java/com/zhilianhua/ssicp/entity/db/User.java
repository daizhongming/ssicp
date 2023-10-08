package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String name;
    private String password;
    private String nickName;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String roleName;
    private String phone;
    private Integer systemFlag;
}
