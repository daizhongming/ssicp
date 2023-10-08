package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;

/**
 * @author
 * @description message
 * @date 2023-08-25
 */
@Data
public class Message {


    private Long id;


    private String title;


    private Date createTime;


    private Date updateTime;


    private String userName;


    private int readFlag;


    private String type;
    private String analyseType;


    private String businessId;

}