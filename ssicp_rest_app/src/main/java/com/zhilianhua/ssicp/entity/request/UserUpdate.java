package com.zhilianhua.ssicp.entity.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdate {
    @NotBlank(message = "昵称不能为空")
    @Length(max = 5, message = "昵称最多只能5个字符")
    private String nickName;
}
