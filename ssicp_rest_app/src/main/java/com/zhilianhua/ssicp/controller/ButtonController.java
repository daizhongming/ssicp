package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.Button;
import com.zhilianhua.ssicp.entity.request.searchparams.ButtonParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.ButtonService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/buttons")
@Privilege(value = "按钮管理")
public class ButtonController {
    @Autowired
    private ButtonService buttonService;

    @GetMapping("")
    @Privilege(value = "查询全部按钮")
    public ResponseEntity<PageResultEntity<Button>> findPage(ButtonParams buttonParams) {
        return ResponseEntityUtils.success(buttonService.findPage(buttonParams));
    }

    @GetMapping("/{id}")
    @Privilege(value = "查询按钮详细")
    public ResponseEntity<Button> findByid(@PathVariable String id) {
        return ResponseEntityUtils.success(buttonService.findByid(id));
    }

    @DeleteMapping("/{id}")
    @Privilege(value = "删除按钮")
    public ResponseEntity delete(@PathVariable String id) {
        buttonService.delete(id);
        return ResponseEntityUtils.success();
    }

    @PostMapping()
    @Privilege(value = "添加按钮")
    public ResponseEntity add(@RequestBody @Valid Button button) {
        buttonService.add(button);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{id}")
    @Privilege(value = "修改按钮")
    public ResponseEntity update(@RequestBody @Valid Button button) {
        buttonService.update(button);
        return ResponseEntityUtils.success();
    }

    @GetMapping("/all")
    @Privilege(value = "查询全部按钮")
    public ResponseEntity<List<Button>> findAll() {
        return ResponseEntityUtils.success(buttonService.findAll());
    }
}