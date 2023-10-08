package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.request.UserPrivilegesAdd;
import com.zhilianhua.ssicp.service.PrivilegeService;
import com.zhilianhua.ssicp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeManage {
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private UserService userService;

    public void addUserPrivileges(UserPrivilegesAdd userPrivilegesAdd) {
        userService.updateRoleName(userPrivilegesAdd.getUserName(), userPrivilegesAdd.getRoleName());
        privilegeService.addUserPrivileges(userPrivilegesAdd);
    }
}
