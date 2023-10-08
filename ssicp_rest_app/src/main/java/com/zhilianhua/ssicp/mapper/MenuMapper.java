package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.Menu;
import com.zhilianhua.ssicp.entity.request.MenuAdd;
import com.zhilianhua.ssicp.entity.request.MenuUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.MenuSearchParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    List<Menu> findByParams(MenuSearchParams menuSearchParams);

    void add(MenuAdd menuAdd);

    void delete(@Param("code") String code);

    void update(@Param("code") String code, @Param("params") MenuUpdate menuUpdate);

}
