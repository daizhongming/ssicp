package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.OperationalAnalyticsService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代忠明
 * @description 智能运营分析
 * @date 2023-07-03
 */
@RestController
@RequestMapping(value = "/operationalAnalytics")
@Privilege("智能运营分析")
public class OperationalAnalyticsController {
    @Autowired
    private OperationalAnalyticsService operationalAnalyticsService;

    @GetMapping("")
    @Privilege("查询全部")
    public ResponseEntity findAll() {
        return ResponseEntityUtils.success(operationalAnalyticsService.findAll());
    }

}