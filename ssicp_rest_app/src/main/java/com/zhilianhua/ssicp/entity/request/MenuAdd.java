package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MenuAdd {
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    private String parentCode;
    private Integer level;
    @NotBlank(message = "编码不能为空")
//    @Pattern(regexp = "[a-zA-Z]*", message = "编码只能是英文字母")
    private String code;
//    @Pattern(regexp = "[a-zA-Z]*", message = "路由名称只能是英文字母")
}
