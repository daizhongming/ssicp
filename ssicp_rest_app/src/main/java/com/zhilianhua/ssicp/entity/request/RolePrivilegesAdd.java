package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RolePrivilegesAdd {
    private List<String> menuCodes;
    private List<String> buttonIds;
    @NotBlank(message = "角色不能为空")
    private String roleName;
}
