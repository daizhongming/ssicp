package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.InspectionException;
import com.zhilianhua.ssicp.entity.request.InspectionPageReqParams;
import com.zhilianhua.ssicp.entity.response.AnalyseVo;
import com.zhilianhua.ssicp.entity.response.InspectionExceptionVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

/**
 * <p>
 * 巡检异常表 服务类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-20
 */
public interface InspectionExceptionService {

    PageResultEntity pageList(InspectionPageReqParams inspectionPageReqParams);

    InspectionExceptionVO load(String id);

    void updateState(InspectionExceptionVO inspectionExceptionVO);

    void add(InspectionException inspectionException);

}
