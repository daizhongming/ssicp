package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Menu;
import com.zhilianhua.ssicp.entity.request.MenuAdd;
import com.zhilianhua.ssicp.entity.request.MenuUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.MenuSearchParams;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.mapper.MenuMapper;
import com.zhilianhua.ssicp.service.ButtonService;
import com.zhilianhua.ssicp.service.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ButtonService buttonService;

    @Override
    public List<Menu> findAll() {
        MenuSearchParams menuSearchParams = new MenuSearchParams();
        return menuMapper.findByParams(menuSearchParams);
    }

    @Override
    public void add(MenuAdd menuAdd) {
        MenuSearchParams menuSearchParams = new MenuSearchParams();
        menuSearchParams.setCode(menuAdd.getCode());
        List<Menu> menus = menuMapper.findByParams(menuSearchParams);
        if (CollectionUtils.isNotEmpty(menus)) {
            throw BusinessException.instance("编码不能重复");
        }
        menuMapper.add(menuAdd);
    }

    @Override
    public void delete(String code) {
        MenuSearchParams menuSearchParams = new MenuSearchParams();
        menuSearchParams.setParentCode(code);
        List<Menu> menus = menuMapper.findByParams(menuSearchParams);
        if (CollectionUtils.isNotEmpty(menus)) {
            throw BusinessException.instance("请先删除子菜单");
        }
        menuMapper.delete(code);
    }

    @Override
    public void update(String code, MenuUpdate menuUpdate) {
        menuMapper.update(code, menuUpdate);
    }
}
