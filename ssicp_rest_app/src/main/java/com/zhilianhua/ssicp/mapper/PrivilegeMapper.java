package com.zhilianhua.ssicp.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivilegeMapper {
    void addUserMenus(@Param("userName") String userName, @Param("menuCodes") List<String> menusCodes);

    void deleteUserMenus(@Param("userName") String userName);

    List<String> findUserMenus(@Param("userName") String userName);


    void addUserButtons(@Param("userName") String userName, @Param("buttonIds") List<String> buttonIds);

    void deleteUserButtons(@Param("userName") String userName);

    List<String> findUserButtons(@Param("userName") String userName);


    void addRoleButtons(@Param("roleName") String roleName, @Param("buttonIds") List<String> buttonIds);

    List<String> findRoleButtons(@Param("roleName") String roleName);

    void deleteRoleButtons(@Param("roleName") String roleName);
    List<String> findRoleMenus(@Param("roleName") String roleName);

    void deleteRoleMenus(@Param("roleName") String roleName);

    void addRoleMenus(@Param("roleName") String userName, @Param("menuCodes") List<String> menuCodes);
}
