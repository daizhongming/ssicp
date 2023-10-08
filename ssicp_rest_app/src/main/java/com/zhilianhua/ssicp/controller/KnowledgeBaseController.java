package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.KnowledgeBase;
import com.zhilianhua.ssicp.entity.db.ManualInspectionPlan;
import com.zhilianhua.ssicp.entity.request.KnowledgeBaseDTO;
import com.zhilianhua.ssicp.entity.request.searchparams.KnowledgeBaseParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.KnowledgeBaseService;
import com.zhilianhua.ssicp.utils.IdUtils;
import com.zhilianhua.ssicp.utils.RequestUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description 知识库
 * @date 2023-08-18
 */
@RestController
@RequestMapping(value = "/knowledgeBases")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @GetMapping("")
    @Privilege(value = "分页查询")
    public ResponseEntity<PageResultEntity<ManualInspectionPlan>> findPage(KnowledgeBaseParams knowledgeBaseParams) {
        return ResponseEntityUtils.success(knowledgeBaseService.findPage(knowledgeBaseParams));
    }

    @GetMapping("getdevice")
    @Privilege(value = "设备列表")
    public ResponseEntity<Map<String, Object>> getdevice() {
        return ResponseEntityUtils.success(knowledgeBaseService.getdevice());
    }

    @PostMapping()
    @Privilege(value = "添加知识库")
    public ResponseEntity insert(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO, @RequestHeader(value = RequestUtils.USER_NAME_KEY, required = false) String userName) {
        knowledgeBaseDTO.getKnowledgeBase().setResponsibleName(userName);
        knowledgeBaseDTO.getKnowledgeBase().setId(IdUtils.uuid());
        knowledgeBaseService.insert(knowledgeBaseDTO);
        return ResponseEntityUtils.success();
    }

    @PutMapping()
    @Privilege(value = "修改知识库")
    public ResponseEntity update(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO, @RequestHeader(value = RequestUtils.USER_NAME_KEY, required = false) String userName) {
        knowledgeBaseService.update(knowledgeBaseDTO);
        return ResponseEntityUtils.success();
    }

    @GetMapping("echo/{id}")
    @Privilege(value = "回显")
    public ResponseEntity echo(@PathVariable String id){
        return ResponseEntityUtils.success(knowledgeBaseService.echo(id));
    }

    @GetMapping("getPushRecords/{id}")
    @Privilege(value = "推送记录")
    public ResponseEntity getPushRecords(@PathVariable String id){
        return ResponseEntityUtils.success(knowledgeBaseService.getPushRecords(id));
    }

    @DeleteMapping("{id}")
    @Privilege(value = "删除")
    public ResponseEntity delete(@PathVariable String id){
        knowledgeBaseService.delete(id);
        return ResponseEntityUtils.success();
    }

    @PutMapping("readIncreasing/{id}")
    @Privilege(value = "查看次数递增")
    public ResponseEntity readIncreasing(@PathVariable String id){
        knowledgeBaseService.readIncreasing(id);
        return ResponseEntityUtils.success();
    }

}