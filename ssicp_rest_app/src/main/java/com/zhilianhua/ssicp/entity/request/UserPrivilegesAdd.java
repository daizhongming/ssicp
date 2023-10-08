package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserPrivilegesAdd extends RolePrivilegesAdd {
    @NotBlank(message = "用户不能为空")
    private String userName;
}
