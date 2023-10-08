package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Menu;
import com.zhilianhua.ssicp.entity.request.MenuAdd;
import com.zhilianhua.ssicp.entity.request.MenuUpdate;

import java.util.List;

public interface MenuService {
    List<Menu> findAll();

    void add(MenuAdd menuAdd);

    void delete(String code);

    void update(String code, MenuUpdate menuUpdate);
}
