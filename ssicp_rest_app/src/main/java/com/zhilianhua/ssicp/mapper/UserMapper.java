package com.zhilianhua.ssicp.mapper;


import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.UserAdd;
import com.zhilianhua.ssicp.entity.request.UserUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.UserSearchParams;
import com.zhilianhua.ssicp.entity.response.UserVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    PageResultEntity<UserVo> search(UserSearchParams userSearchParams);

    void add(UserAdd userAdd);

    void update(@Param("name") String name, @Param("user") UserUpdate userAdd);

    void delete(@Param("name") String name);

    UserVo findByName(@Param("name") String name);

    List<User> selectUsersWithRoleName(@Param("roleName") String roleName);

    void changePassword(@Param("name") String name, @Param("hashpw") String hashpw);

    void updateRoleName(@Param("userName") String userName, @Param("roleName") String roleName);
}
