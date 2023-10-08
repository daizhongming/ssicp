package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.Menu;
import com.zhilianhua.ssicp.entity.request.MenuAdd;
import com.zhilianhua.ssicp.entity.request.MenuUpdate;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.MenuService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/menus")
@Privilege("菜单管理")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("")
    @Privilege(value = "查询全部菜单")
    public ResponseEntity<List<Menu>> findAll() {
        return ResponseEntityUtils.success(menuService.findAll());
    }


    @PostMapping("")
    @Privilege(value = "新增菜单")
    public ResponseEntity add(@RequestBody @Valid MenuAdd menuAdd) {
        menuService.add(menuAdd);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{code}")
    @Privilege(value = "修改菜单")
    public ResponseEntity update(@PathVariable String code, @RequestBody @Valid MenuUpdate menuUpdate) {
        menuService.update(code, menuUpdate);
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("/{code}")
    @Privilege(value = "删除菜单")
    public ResponseEntity delete(@PathVariable String code) {
        menuService.delete(code);
        return ResponseEntityUtils.success();
    }

}
