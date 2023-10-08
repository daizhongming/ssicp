package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.Config;
import com.zhilianhua.ssicp.entity.request.searchparams.ConfigParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.ConfigService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/configs")
@Privilege("参数管理")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @GetMapping("")
    @Privilege(value = "分页查询全部参数")
    public ResponseEntity<PageResultEntity<Config>> findPage(ConfigParams configParams) {
        return ResponseEntityUtils.success(configService.findPage(configParams));
    }

    @GetMapping("/all")
    @Privilege(value = "查询全部参数")
    public ResponseEntity<List<Config>> findAll() {
        return ResponseEntityUtils.success(configService.findAll());
    }

    @PostMapping("")
    @Privilege(value = "新增")
    public ResponseEntity add(@RequestBody @Valid Config config) {
        configService.add(config);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{id}")
    @Privilege(value = "修改")
    public ResponseEntity update(@RequestBody @Valid Config config) {
        configService.update(config);
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("/{id}")
    @Privilege(value = "删除")
    public ResponseEntity delete(@PathVariable String id) {
        configService.delete(id);
        return ResponseEntityUtils.success();
    }
}