package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description 管段保温信息
 * @author zhengkai.blog.csdn.net
 * @date 2023-08-23
 */
@Data
public class PipeInsulation implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 管段id
     */
    private String pipeId;

    /**
     * 保温层材料
     */
    @NotBlank(message = "保温层材料不能为空")
    private String insulationMaterial;

    /**
     * 保温工艺
     */
    @NotBlank(message = "保温工艺不能为空")
    private String insulation;

    /**
     * 保温厚度（mm）
     */
    @NotNull(message = "保温厚度不能为空")
    private Integer insulationMaterialThickness;

    /**
     * 使用年限
     */
    @NotNull(message = "使用年限不能为空")
    private Integer ageLimit;


}