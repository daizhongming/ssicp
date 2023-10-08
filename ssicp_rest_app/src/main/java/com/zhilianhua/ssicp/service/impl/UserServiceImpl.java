package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.UserAdd;
import com.zhilianhua.ssicp.entity.request.UserUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.UserSearchParams;
import com.zhilianhua.ssicp.entity.response.UserVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.mapper.UserMapper;
import com.zhilianhua.ssicp.service.ConfigService;
import com.zhilianhua.ssicp.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConfigService configService;

    @Override
    public PageResultEntity<UserVo> search(UserSearchParams userSearchParams) {
        return userMapper.search(userSearchParams);
    }

    @Override
    public void add(UserAdd userAdd) {
        User user = findByName(userAdd.getName());
        if (Objects.nonNull(user)) {
            throw BusinessException.instance("用户名不能重复");
        }
        userAdd.setPassword(BCrypt.hashpw(configService.getInitPassword(), BCrypt.gensalt()));
        userMapper.add(userAdd);
    }

    @Override
    public void update(String name, UserUpdate userAdd) {
        userMapper.update(name, userAdd);
    }

    @Override
    public void delete(String name) {
        userMapper.delete(name);
    }

    @Override
    public UserVo findByName(String name) {
        UserVo byName = userMapper.findByName(name);
        if (Objects.nonNull(byName)) {
            byName.setRoot(StringUtils.equals(getRootName(), name));
            byName.setInspector(StringUtils.equals(getInspectorRoleName(), byName.getRoleName()));
        }
        return byName;
    }

    @Value("${app.account.root}")
    private String rootName;

    @Override
    public String getRootName() {
        return rootName;
    }

    @Value("${app.account.anonymous}")
    private String anonymous;

    @Override
    public String getAnonymousName() {
        return anonymous;
    }

    @Override
    public void changePassword(String userName, String oldpassword, String newpasswprd, String repeatpasswprd) {
        //校验旧密码
        User byName = userMapper.findByName(userName);
        if (!BCrypt.checkpw(oldpassword, byName.getPassword())) {
            throw BusinessException.instance("旧密码错误");
        }
        //校验新密码
        if (!newpasswprd.equals(repeatpasswprd)) {
            throw BusinessException.instance("重复密码不正确");
        }
        userMapper.changePassword(byName.getName(), BCrypt.hashpw(newpasswprd, BCrypt.gensalt()));
    }

    @Override
    public void updateRoleName(String userName, String roleName) {
        userMapper.updateRoleName(userName, roleName);
    }

    @Value("${app.account.inspectorRoleName}")
    private String inspectorRoleName;

    public List<User> findAllInspector() {
        return userMapper.selectUsersWithRoleName(getInspectorRoleName());
    }

    private String getInspectorRoleName() {
        return inspectorRoleName;
    }

    @Override
    public boolean isInspector(String userName) {
        User user = findByName(userName);
        return Objects.nonNull(user) && StringUtils.equals(getInspectorRoleName(), user.getRoleName());
    }
}
