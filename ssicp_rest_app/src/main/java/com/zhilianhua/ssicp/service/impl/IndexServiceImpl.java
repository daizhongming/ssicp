package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Button;
import com.zhilianhua.ssicp.entity.db.Menu;
import com.zhilianhua.ssicp.entity.response.LoginMessageVo;
import com.zhilianhua.ssicp.entity.response.PrivilegesVo;
import com.zhilianhua.ssicp.entity.response.UserVo;
import com.zhilianhua.ssicp.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @Autowired
    private ButtonService buttonService;
    @Autowired
    private DictTypeService dictTypeService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private ConfigService configService;

    @Override
    public LoginMessageVo getMessage(String userName) {
        LoginMessageVo loginMessageVo = new LoginMessageVo();
        if (StringUtils.isBlank(userName)) {
            //未登录
            PrivilegesVo userPrivileges = privilegeService.findUserPrivileges(userService.getAnonymousName());
            loginMessageVo.setMenuCodeList(userPrivileges.getMenuCodes());
            loginMessageVo.setButtonIdList(userPrivileges.getButtonIds());
        } else {
            UserVo userVo = userService.findByName(userName);
            loginMessageVo.setUserVo(userVo);
            //已登陆
            if (userVo.isRoot()) {
                loginMessageVo.setMenuCodeList(menuService.findAll().stream().map(Menu::getCode).collect(Collectors.toList()));
                loginMessageVo.setButtonIdList(buttonService.findAll().stream().map(Button::getId).collect(Collectors.toList()));
            } else {
                PrivilegesVo userPrivileges = privilegeService.findUserPrivileges(userName);
                loginMessageVo.setMenuCodeList(userPrivileges.getMenuCodes());
                loginMessageVo.setButtonIdList(userPrivileges.getButtonIds());
            }
        }
        return loginMessageVo;
    }
}
