package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Role;
import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

public interface RoleService {
    void insert(Role role);

    void delete(String name);

    void update(Role role);

    Role load(String name);

    PageResultEntity<Role> pageList(PageParams pageParams, String keyword);

    List<Role> findAll();
}
