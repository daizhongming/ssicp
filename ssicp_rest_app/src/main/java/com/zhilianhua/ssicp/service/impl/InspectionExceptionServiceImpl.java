package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.DeviceAbnormalLog;
import com.zhilianhua.ssicp.entity.db.InspectionException;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.InspectionPageReqParams;
import com.zhilianhua.ssicp.entity.response.DeviceAbnormalLogVO;
import com.zhilianhua.ssicp.entity.response.InspectionExceptionVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.InspectionExceptionMapper;
import com.zhilianhua.ssicp.service.DeviceAbnormalLogService;
import com.zhilianhua.ssicp.service.DeviceIndexLogService;
import com.zhilianhua.ssicp.service.InspectionExceptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 巡检异常表 服务实现类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-20
 */
@Service
public class InspectionExceptionServiceImpl implements InspectionExceptionService {
    @Autowired
    private InspectionExceptionMapper inspectionExceptionMapper;

    @Autowired
    private DeviceAbnormalLogService deviceAbnormalLogService;

    @Autowired
    private DeviceIndexLogService deviceIndexLogService;

    @Override
    public PageResultEntity pageList(InspectionPageReqParams inspectionPageReqParams) {
        List<InspectionException> inspectionExceptions = inspectionExceptionMapper.pageList(inspectionPageReqParams);
        long total = inspectionExceptionMapper.pageListCount(inspectionPageReqParams);
        PageResultEntity result = new PageResultEntity(total, inspectionExceptions);
        return result;
    }


    @Override
    public InspectionExceptionVO load(String id) {
        InspectionExceptionVO inspectionException = inspectionExceptionMapper.selectById(id);
        List<DeviceAbnormalLogVO> deviceAbnormalLogVO = deviceAbnormalLogService.searchByeid(id);
        inspectionException.setDeviceAbnormalLogVO(deviceAbnormalLogVO);
        return inspectionException;
    }

    @Override
    public void updateState(InspectionExceptionVO inspectionExceptionVO) {
        //完成处理
        if (inspectionExceptionVO.getDisposeState() == 1) {
            //修改异常状态
            InspectionException inspectionException = new InspectionException();
            BeanUtils.copyProperties(inspectionExceptionVO, inspectionException);
            inspectionExceptionMapper.update(inspectionException);

            //修改异常指标记录
            List<DeviceAbnormalLogVO> deviceAbnormalLogVO = inspectionExceptionVO.getDeviceAbnormalLogVO();
            DeviceAbnormalLog deviceAbnormalLog = new DeviceAbnormalLog();
            DeviceIndexLog deviceIndexLog = new DeviceIndexLog();
            for (DeviceAbnormalLogVO abnormalLogVO : deviceAbnormalLogVO) {
                deviceAbnormalLog.setId(abnormalLogVO.getId());
                deviceAbnormalLog.setComments(abnormalLogVO.getComments());
                deviceAbnormalLog.setCorrection(abnormalLogVO.getCorrection());
                deviceAbnormalLogService.update(deviceAbnormalLog);

                //添加修正记录
                deviceIndexLog.setValue(abnormalLogVO.getCorrection());
                deviceIndexLog.setIndexId(abnormalLogVO.getIndexId());
                deviceIndexLog.setDeviceId(abnormalLogVO.getDeviceId());
                deviceIndexLogService.add(deviceIndexLog);
            }
        }
        //忽略处理
        else if (inspectionExceptionVO.getDisposeState() == 2) {
            //修改异常状态
            InspectionException inspectionException = new InspectionException();
            BeanUtils.copyProperties(inspectionException, inspectionExceptionVO);
            inspectionExceptionMapper.update(inspectionException);
        }
    }

    @Override
    public void add(InspectionException inspectionException) {
        inspectionExceptionMapper.add(inspectionException);
    }
}
