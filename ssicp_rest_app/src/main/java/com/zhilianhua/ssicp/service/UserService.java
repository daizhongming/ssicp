package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.UserAdd;
import com.zhilianhua.ssicp.entity.request.UserPrivilegesAdd;
import com.zhilianhua.ssicp.entity.request.UserUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.UserSearchParams;
import com.zhilianhua.ssicp.entity.response.PrivilegesVo;
import com.zhilianhua.ssicp.entity.response.UserVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

public interface UserService {
    PageResultEntity<UserVo> search(UserSearchParams userSearchParams);

    void add(UserAdd userAdd);

    void update(String name, UserUpdate userAdd);

    void delete(String name);

    UserVo findByName(String name);

    String getRootName();

    String getAnonymousName();

    void changePassword(String userName, String oldpassword, String newpasswprd, String repeatpasswprd);

    void updateRoleName(String userName, String roleName);

    List<User> findAllInspector();
    boolean isInspector(String userName);
}
