package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author 代忠明
 * @description 字典数据表
 * @date 2023-06-28
 */
@Data
public class DictData {

    /**
     * 主键
     */
    private Long id;

    /**
     * 字典排序
     */
    @NotNull(message = "字典排序不能为空")
    private Integer dictSort;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典名称不能为空")
    private String dictLabel;

    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    private String dictValue;

    /**
     * 类型code
     */
    @NotBlank(message = "类型不能为空")
    private String typeCode;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 字典数据父级id
     */
    private Long parentId;

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
    private String expand2;
    private String expand3;
}