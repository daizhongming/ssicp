package com.zhilianhua.ssicp.controller;


import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.db.Device;
import com.zhilianhua.ssicp.entity.db.Pipe;
import com.zhilianhua.ssicp.entity.request.DeviceDTO;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DeviceAlarmService;
import com.zhilianhua.ssicp.service.DeviceService;
import com.zhilianhua.ssicp.service.PipeService;
import com.zhilianhua.ssicp.service.impl.PipeManage;
import com.zhilianhua.ssicp.utils.IdUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管段表 前端控制器
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@RestController
@Privilege("管段管理")
@RequestMapping("/pipes")
public class PipeController {
    @Autowired
    private PipeService pipeService;

    @Autowired
    private PipeManage pipeManage;

    /**
     * 查询 分页查询
     *
     * @author 代忠明
     * @date 2023/06/18
     **/
    @GetMapping("")
    @Privilege(value = "分页查询管段")
    public ResponseEntity pageList(PipelinePageReqSearchParams pageReqSearchParams) {
        PageResultEntity pageResultEntity = pipeManage.pageList(pageReqSearchParams);
        return ResponseEntityUtils.success(pageResultEntity);
    }

    /**
     * 刪除
     *
     * @author 代忠明
     * @date 2023/06/19
     **/
    @DeleteMapping("/{id}")
    @Privilege(value = "删除管段")
    public ResponseEntity del(@PathVariable String id) {
        pipeManage.del(id);
        return ResponseEntityUtils.success();
    }

    /**
     * 查询管段下的设备及指标项
     *
     * @author 代忠明
     * @date 2023/06/19
     **/
    @GetMapping("/devicesandAlarm/{id}")
    @Privilege(value = "查询管段下的设备及指标项")
    public ResponseEntity devicesandAlarm(@PathVariable String id) {
        return ResponseEntityUtils.success(pipeService.devicesandAlarm(id));
    }
}
