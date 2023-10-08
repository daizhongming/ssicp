package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ButtonAdd {
    @NotBlank(message = "编码不能为空")
    private String id;
    @NotBlank(message = "描述不能为空")
    private String description;
    @NotBlank(message = "菜单码不能为空")
    private String menuCode;
}
