package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description 产用汽点管理
 * @date 2023-08-30
 */
@Data
public class DeviceGasPoint implements Serializable {

    /**
    * id
    */
    private String id;

    /**
    * 所属区域
    */
    private String region;

    /**
    * 出入口管道
    */
    @NotBlank(message = "出入口管道不能为空")
    private String pid;

    public DeviceGasPoint() {
    }

}