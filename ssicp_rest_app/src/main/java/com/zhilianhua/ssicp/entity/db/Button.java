package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Button {

    private static final long serialVersionUID = 1L;

    /**
     * 按钮id
     */
    @NotBlank(message = "编码不能为空")
    private String id;

    /**
     * 所属菜单
     */
    @NotBlank(message = "类型不能为空")
    private String type;

    /**
     * 按钮描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    public Button() {
    }

}