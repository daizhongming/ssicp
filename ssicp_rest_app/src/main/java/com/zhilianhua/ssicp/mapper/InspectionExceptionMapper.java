package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.InspectionException;
import com.zhilianhua.ssicp.entity.request.InspectionPageReqParams;
import com.zhilianhua.ssicp.entity.response.AnalyseVo;
import com.zhilianhua.ssicp.entity.response.InspectionExceptionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 巡检异常表 Mapper 接口
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-20
 */
public interface InspectionExceptionMapper {
    List<InspectionException> pageList(InspectionPageReqParams inspectionPageReqParams);

    long pageListCount(InspectionPageReqParams inspectionPageReqParams);

    InspectionExceptionVO selectById(@Param("id") String id);

    void update(InspectionException inspectionException);

    void add(InspectionException inspectionException);
}
