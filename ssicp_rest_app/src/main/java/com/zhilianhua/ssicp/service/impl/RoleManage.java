package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.service.PrivilegeService;
import com.zhilianhua.ssicp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleManage {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;


    @Transactional(rollbackFor = Exception.class)
    public void delete(String name) {
        roleService.delete(name);
        privilegeService.deleteRolePrivileges(name);
    }
}
