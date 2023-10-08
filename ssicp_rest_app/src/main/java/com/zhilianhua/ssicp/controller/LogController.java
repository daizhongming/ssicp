package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.Log;
import com.zhilianhua.ssicp.entity.request.searchparams.LogParams;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.LogService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @description 日志记录表
 * @date 2023-07-07
 */
@RestController
@RequestMapping(value = "/logs")
@Privilege("日志管理")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("")
    @Privilege(value = "查询全部日志")
    public ResponseEntity<List<Log>> search(LogParams logParams) {
        return ResponseEntityUtils.success(logService.search(logParams));
    }
}