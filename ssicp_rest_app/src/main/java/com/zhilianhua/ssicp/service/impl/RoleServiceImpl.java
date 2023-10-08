package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Role;
import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.mapper.RoleMapper;
import com.zhilianhua.ssicp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void insert(Role role) {
        Role load = load(role.getName());
        if (Objects.nonNull(load)) {
            throw BusinessException.instance("角色编码重复");
        }
        Role byTitle = roleMapper.getByTitle(role.getTitle());
        if (Objects.nonNull(byTitle)) {
            throw BusinessException.instance("角色名称重复");
        }
        roleMapper.insert(role);
    }


    @Override
    public void delete(String name) {
        roleMapper.del(name);
    }


    @Override
    public void update(Role role) {
        Role byTitle = roleMapper.getByTitle(role.getTitle());
        if (Objects.nonNull(byTitle) && !byTitle.getName().equals(role.getName())) {
            throw BusinessException.instance("角色名称重复");
        }
        roleMapper.update(role);
    }

    @Override
    public Role load(String name) {
        return roleMapper.load(name);
    }

    @Override
    public PageResultEntity<Role> pageList(PageParams pageParams, String keyword) {
        return roleMapper.search(pageParams.getOffset(), pageParams.getSize(), keyword);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
