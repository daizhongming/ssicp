package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description 角色表
 * @author 代忠明
 * @date 2023-06-28
 */
@Data
public class Role{


    /**
     * 角色name
     */
    @NotBlank(message = "角色编码不能为空")
    private String name;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String title;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;
    private Integer systemFlag;


}