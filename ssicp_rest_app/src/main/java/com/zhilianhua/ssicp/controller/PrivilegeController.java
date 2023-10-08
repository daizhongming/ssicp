package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.request.RolePrivilegesAdd;
import com.zhilianhua.ssicp.entity.request.UserPrivilegesAdd;
import com.zhilianhua.ssicp.entity.response.PrivilegesVo;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.PrivilegeService;
import com.zhilianhua.ssicp.service.impl.PrivilegeManage;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/privileges")
@Privilege("权限管理")
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private PrivilegeManage privilegeManage;

    @PostMapping("/users/{userName}")
    @Privilege(value = "修改用户权限")
    public ResponseEntity addUserPrivileges(@RequestBody @Valid UserPrivilegesAdd userPrivilegesAdd) {
        privilegeManage.addUserPrivileges(userPrivilegesAdd);
        return ResponseEntityUtils.success();
    }

    @GetMapping("/users/{userName}")
    @Privilege(value = "查询用户权限")
    public ResponseEntity<PrivilegesVo> findUserPrivileges(@PathVariable("userName") String userName) {
        return ResponseEntityUtils.success(privilegeService.findUserPrivileges(userName));
    }

    @GetMapping("/roles/{roleName}")
    @Privilege(value = "查询角色权限")
    public ResponseEntity<PrivilegesVo> findRolePrivileges(@PathVariable("roleName") String roleName) {
        return ResponseEntityUtils.success(privilegeService.findRolePrivileges(roleName));
    }

    @PostMapping("/roles/{roleName}")
    @Privilege(value = "修改角色权限")
    public ResponseEntity addRolePrivileges(@RequestBody @Valid RolePrivilegesAdd rolePrivilegesAdd) {
        privilegeService.addRolePrivileges(rolePrivilegesAdd);
        return ResponseEntityUtils.success();
    }
}
