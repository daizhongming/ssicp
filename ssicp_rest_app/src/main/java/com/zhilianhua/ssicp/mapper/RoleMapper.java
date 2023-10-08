package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.Role;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 角色表
 * @author 代忠明
 * @date 2023-06-28
 */
public interface RoleMapper {
    void insert(Role role);

    int del(@Param("name") String name);

    int update(Role role);

    Role load(@Param("name") String name);
    Role getByTitle(@Param("title") String title);

    List<Role> findAll();

    PageResultEntity<Role> search(@Param("offset") int offset, @Param("size") int size, @Param("keyword") String keyword);
}