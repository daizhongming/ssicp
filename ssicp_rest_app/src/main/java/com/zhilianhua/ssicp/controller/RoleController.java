package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.Role;
import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.RoleService;
import com.zhilianhua.ssicp.service.impl.RoleManage;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Privilege("角色管理")
@RequestMapping("roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleManage roleManage;

    @GetMapping("")
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntityUtils.success(roleService.findAll());
    }

    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/28
     **/
    @PostMapping("")
    @Privilege(value = "添加角色")
    public ResponseEntity insert(@Valid @RequestBody Role role) {
        roleService.insert(role);
        return ResponseEntityUtils.success();
    }

    /**
     * 刪除
     *
     * @author 代忠明
     * @date 2023/06/28
     **/
    @DeleteMapping("/{name}")
    @Privilege(value = "删除角色")
    public ResponseEntity delete(@PathVariable String name) {
        roleManage.delete(name);
        return ResponseEntityUtils.success();
    }

    /**
     * 更新
     *
     * @author 代忠明
     * @date 2023/06/28
     **/
    @PutMapping("/{name}")
    @Privilege(value = "修改角色")
    public ResponseEntity update(@Valid @RequestBody Role role) {
        roleService.update(role);
        return ResponseEntityUtils.success();
    }

    /**
     * 查询 根据主键 id 查询
     *
     * @author 代忠明
     * @date 2023/06/28
     **/
    @GetMapping("/{name}")
    @Privilege(value = "角色详情")
    public ResponseEntity load(@PathVariable String name) {
        Role load = roleService.load(name);
        return ResponseEntityUtils.success(load);
    }

    /**
     * 查询 分页查询
     *
     * @author 代忠明
     * @date 2023/06/28
     **/
    @GetMapping("/pageList")
    @Privilege(value = "角色分页")
    public ResponseEntity pageList(PageParams pageParams, String keyword) {
        return ResponseEntityUtils.success(roleService.pageList(pageParams, keyword));
    }
}
