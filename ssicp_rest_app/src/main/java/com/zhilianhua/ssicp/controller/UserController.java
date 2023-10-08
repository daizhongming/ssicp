package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.UserAdd;
import com.zhilianhua.ssicp.entity.request.UserUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.UserSearchParams;
import com.zhilianhua.ssicp.entity.response.UserVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.UserService;
import com.zhilianhua.ssicp.service.impl.UserManage;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Privilege("用户管理")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserManage userManage;

    @GetMapping("")
    @Privilege(value = "查询全部用户")
    public ResponseEntity<PageResultEntity<UserVo>> search(UserSearchParams userSearchParams) {
        return ResponseEntityUtils.success(userService.search(userSearchParams));
    }

    @PostMapping("")
    @Privilege(value = "新增用户")
    public ResponseEntity add(@Valid @RequestBody UserAdd userAdd) {
        userService.add(userAdd);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{name}")
    @Privilege(value = "修改用户")
    public ResponseEntity update(@PathVariable("name") String name, @RequestBody @Valid UserUpdate userAdd) {
        userService.update(name, userAdd);
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("/{name}")
    @Privilege(value = "删除用户")
    public ResponseEntity delete(@PathVariable("name") String name) {
        userManage.delete(name);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/changePassword")
    @Privilege(value = "修改密码")
    public ResponseEntity changePassword(@RequestParam String userName,
                                         @RequestParam String oldpassword,
                                         @RequestParam String newpasswprd,
                                         @RequestParam String repeatpasswprd
    ) {
        userService.changePassword(userName, oldpassword, newpasswprd, repeatpasswprd);
        return ResponseEntityUtils.success();
    }

    @GetMapping("/inspectors")
    @Privilege(value = "查询点检员")
    public ResponseEntity<List<User>> findAllInspector() {
        return ResponseEntityUtils.success(userService.findAllInspector());
    }
}
