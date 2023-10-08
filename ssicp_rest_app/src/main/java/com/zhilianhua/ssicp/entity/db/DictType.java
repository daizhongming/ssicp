package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author 代忠明
 * @description 字典类型表
 * @date 2023-06-28
 */
@Data
public class DictType {
    /**
     * 字典编号
     */
    @NotBlank(message = "字典编码不能为空")
    private String code;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

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
    private String expand1;
    private String expandType1;
    private String expand2;
    private String expandType2;
    private String expand3;
    private String expandType3;
    private String privileges;

}