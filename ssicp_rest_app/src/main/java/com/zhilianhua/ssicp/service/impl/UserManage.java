package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.service.PrivilegeService;
import com.zhilianhua.ssicp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManage {
    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeService privilegeService;
    public void delete(String name) {
        userService.delete(name);
        privilegeService.deleteUserPrivileges(name);
    }
}
