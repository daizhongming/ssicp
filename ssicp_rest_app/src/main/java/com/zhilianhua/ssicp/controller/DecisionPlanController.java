package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.DecisionPlanAdd;
import com.zhilianhua.ssicp.entity.request.DecisionPlanUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.DecisionPlanSearchParams;
import com.zhilianhua.ssicp.entity.response.DecisionPlanVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DecisionPlanService;
import com.zhilianhua.ssicp.service.UserService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/decisionPlans")
@Privilege("智能决策预案管理")
public class DecisionPlanController {
    @Autowired
    private DecisionPlanService decisionPlanService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    @Privilege(value = "分页查询")
    public ResponseEntity<PageResultEntity<DecisionPlanVo>> findPage(DecisionPlanSearchParams decisionPlanSearchParams) {
        return ResponseEntityUtils.success(decisionPlanService.findPage(decisionPlanSearchParams));
    }

    @PostMapping("")
    @Privilege(value = "新增")
    public ResponseEntity add(@RequestBody @Valid DecisionPlanAdd decisionPlanAdd, @RequestHeader("userName") String userName) {
        User byName = userService.findByName(userName);
        decisionPlanAdd.setUploadUser(byName.getNickName());
        decisionPlanService.add(decisionPlanAdd);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{id}")
    @Privilege(value = "修改")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid DecisionPlanUpdate decisionPlanUpdate) {
        decisionPlanService.update(id, decisionPlanUpdate);
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("/{id}")
    @Privilege(value = "删除")
    public ResponseEntity delete(@PathVariable Integer id) {
        decisionPlanService.delete(id);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{id}/readCount")
    @Privilege(value = "修改查看次数")
    public ResponseEntity updateReadCount(@PathVariable Integer id) {
        decisionPlanService.updateReadCount(id);
        return ResponseEntityUtils.success();
    }
}
