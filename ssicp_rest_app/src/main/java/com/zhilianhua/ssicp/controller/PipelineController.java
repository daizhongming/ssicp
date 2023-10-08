package com.zhilianhua.ssicp.controller;


import com.zhilianhua.ssicp.entity.db.Pipeline;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.TreeVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.PipelineService;
import com.zhilianhua.ssicp.service.impl.PipeManage;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 管线表 前端控制器
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@RestController
@Privilege("管线管理")
@RequestMapping("/pipelines")
public class PipelineController {
    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private PipeManage pipeManage;

    /**
     * 查询 分页查询
     *
     * @author 代忠明
     * @date 2023/06/18
     **/
    @GetMapping("")
    @Privilege(value = "分页查询管线")
    public ResponseEntity pageList(PipelinePageReqSearchParams pageReqDto) {
        PageResultEntity<Pipeline> pageResultEntity = pipeManage.pageListPipeline(pageReqDto);
        return ResponseEntityUtils.success(pageResultEntity);
    }

    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/19
     **/
    @PostMapping("")
    @Privilege(value = "新增管线")
    public ResponseEntity insert(@Valid @RequestBody Pipeline pipeline) {
        pipelineService.insert(pipeline);
        return ResponseEntityUtils.success();
    }

    /**
     * 刪除
     *
     * @author 代忠明
     * @date 2023/06/19
     **/
    @DeleteMapping("/{id}")
    @Privilege(value = "删除管线")
    public ResponseEntity del(@PathVariable String id) {
        pipelineService.del(id);
        return ResponseEntityUtils.success();
    }

    /**
     * 更新
     *
     * @author 代忠明
     * @date 2023/06/19
     **/
    @PutMapping("/{id}")
    @Privilege(value = "修改管线")
    public ResponseEntity update(@Valid @RequestBody Pipeline pipeline) {
        pipelineService.update(pipeline);
        return ResponseEntityUtils.success();
    }

    /**
     * 查询 根据主键 id 查询
     *
     * @author 代忠明
     * @date 2023/06/19
     **/
    @GetMapping("/{id}")
    @Privilege(value = "查询管线详细")
    public ResponseEntity load(@PathVariable String id) {
        return ResponseEntityUtils.success(pipelineService.load(id));
    }

    /**
     * 查询 压力等级/管线/管段分组
     *
     * @author 代忠明
     * @date 2023/06/18
     **/
    @GetMapping("/pressurePipelineGrouping")
    @Privilege(value = "压力等级/管线/管段分组")
    public ResponseEntity pressurePipelineGrouping(String[] regions) {
        return ResponseEntityUtils.success(pipeManage.pressurePipelineGrouping(Arrays.asList(regions)));
    }

    @GetMapping("/pressureLevelTree")
    @Privilege(value = "管道筛选")
    public ResponseEntity<TreeVo> getPressureLevelTree() {
        return ResponseEntityUtils.success(pipeManage.getPressureLevelTree());
    }

}