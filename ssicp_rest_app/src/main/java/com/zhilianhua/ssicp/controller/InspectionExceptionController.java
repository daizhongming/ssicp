package com.zhilianhua.ssicp.controller;


import com.zhilianhua.ssicp.entity.request.InspectionPageReqParams;
import com.zhilianhua.ssicp.entity.response.InspectionExceptionVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.InspectionExceptionService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 巡检异常表 前端控制器
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-20
 */
@RestController
@RequestMapping("inspectionExceptions")
@Privilege("巡检异常")
public class InspectionExceptionController {
    @Autowired
    private InspectionExceptionService inspectionExceptionService;

    @GetMapping("")
    @Privilege(value = "分页查询")
    public ResponseEntity pageList(InspectionPageReqParams inspectionPageReqParams) {
        PageResultEntity pageResultEntity = inspectionExceptionService.pageList(inspectionPageReqParams);
        return ResponseEntityUtils.success(pageResultEntity);
    }

    /**
     * 查询 根据主键 id 查询
     *
     * @author 代忠明
     * @date 2023/06/20
     **/
    @GetMapping("{id}")
    @Privilege(value = "巡检异常详细")
    public ResponseEntity load(@PathVariable String id) {
        InspectionExceptionVO load = inspectionExceptionService.load(id);
        return ResponseEntityUtils.success(load);
    }

    @PutMapping("updateState")
    @Privilege(value = "巡检异常修改")
    public ResponseEntity updateState(@RequestBody InspectionExceptionVO inspectionExceptionVO) {
        inspectionExceptionService.updateState(inspectionExceptionVO);
        return ResponseEntityUtils.success();
    }


}
