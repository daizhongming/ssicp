package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.request.RolePrivilegesAdd;
import com.zhilianhua.ssicp.entity.request.UserPrivilegesAdd;
import com.zhilianhua.ssicp.entity.response.PrivilegesVo;
import com.zhilianhua.ssicp.mapper.PrivilegeMapper;
import com.zhilianhua.ssicp.service.PrivilegeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public void addUserPrivileges(UserPrivilegesAdd userPrivilegesAdd) {
        privilegeMapper.deleteUserMenus(userPrivilegesAdd.getUserName());
        privilegeMapper.deleteUserButtons(userPrivilegesAdd.getUserName());
        if (CollectionUtils.isNotEmpty(userPrivilegesAdd.getMenuCodes())) {
            privilegeMapper.addUserMenus(userPrivilegesAdd.getUserName(), userPrivilegesAdd.getMenuCodes());
        }
        if (CollectionUtils.isNotEmpty(userPrivilegesAdd.getButtonIds())) {
            privilegeMapper.addUserButtons(userPrivilegesAdd.getUserName(), userPrivilegesAdd.getButtonIds());
        }
    }

    @Override
    public PrivilegesVo findUserPrivileges(String userName) {
        PrivilegesVo privilegesVo = new PrivilegesVo();
        privilegesVo.setMenuCodes(privilegeMapper.findUserMenus(userName));
        privilegesVo.setButtonIds(privilegeMapper.findUserButtons(userName));
        return privilegesVo;
    }

    @Override
    public void deleteUserPrivileges(String userName) {
        privilegeMapper.deleteUserButtons(userName);
        privilegeMapper.deleteUserMenus(userName);
    }

    @Override
    public void deleteRolePrivileges(String roleName) {
        privilegeMapper.deleteRoleButtons(roleName);
        privilegeMapper.deleteRoleMenus(roleName);
    }

    @Override
    public PrivilegesVo findRolePrivileges(String roleName) {
        PrivilegesVo privilegesVo = new PrivilegesVo();
        privilegesVo.setMenuCodes(privilegeMapper.findRoleMenus(roleName));
        privilegesVo.setButtonIds(privilegeMapper.findRoleButtons(roleName));
        return privilegesVo;
    }

    @Override
    public void addRolePrivileges(RolePrivilegesAdd rolePrivilegesAdd) {
        privilegeMapper.deleteRoleMenus(rolePrivilegesAdd.getRoleName());
        privilegeMapper.deleteRoleButtons(rolePrivilegesAdd.getRoleName());
        if (CollectionUtils.isNotEmpty(rolePrivilegesAdd.getMenuCodes())) {
            privilegeMapper.addRoleMenus(rolePrivilegesAdd.getRoleName(), rolePrivilegesAdd.getMenuCodes());
        }
        if (CollectionUtils.isNotEmpty(rolePrivilegesAdd.getButtonIds())) {
            privilegeMapper.addRoleButtons(rolePrivilegesAdd.getRoleName(), rolePrivilegesAdd.getButtonIds());
        }
    }
}
