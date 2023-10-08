package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportAdd;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.BenefitAnalysisReportSearchParams;
import com.zhilianhua.ssicp.entity.response.BenefitAnalysisReportVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.BenefitAnalysisReportService;
import com.zhilianhua.ssicp.service.UserService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/benefitAnalysisReports")
@Privilege("效益分析报告管理")
public class BenefitAnalysisReportController {
    @Autowired
    private BenefitAnalysisReportService benefitAnalysisReportService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    @Privilege(value = "分页查询")
    public ResponseEntity<PageResultEntity<BenefitAnalysisReportVo>> findPage(BenefitAnalysisReportSearchParams benefitAnalysisReportSearchParams) {
        return ResponseEntityUtils.success(benefitAnalysisReportService.findPage(benefitAnalysisReportSearchParams));
    }

    @PostMapping("")
    @Privilege(value = "新增")
    public ResponseEntity add(@RequestBody @Valid BenefitAnalysisReportAdd benefitAnalysisReportAdd, @RequestHeader("userName") String userName) {
        User byName = userService.findByName(userName);
        benefitAnalysisReportAdd.setUploadUser(byName.getNickName());
        benefitAnalysisReportService.add(benefitAnalysisReportAdd);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{id}")
    @Privilege(value = "修改")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid BenefitAnalysisReportUpdate benefitAnalysisReportUpdate) {
        benefitAnalysisReportService.update(benefitAnalysisReportUpdate);
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("/{id}")
    @Privilege(value = "删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        benefitAnalysisReportService.delete(id);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{id}/readCount")
    @Privilege(value = "修改查看次数")
    public ResponseEntity updateReadCount(@PathVariable Integer id) {
        benefitAnalysisReportService.updateReadCount(id);
        return ResponseEntityUtils.success();
    }
}
