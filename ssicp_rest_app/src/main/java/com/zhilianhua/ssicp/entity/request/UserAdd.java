package com.zhilianhua.ssicp.entity.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserAdd {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}", message = "用户名只能是6-20位的字符数字或下划线")
    private String name;
    @NotBlank(message = "昵称不能为空")
    @Length(max = 5, message = "昵称最多只能5个字符")
    private String nickName;
    private String password;
}
