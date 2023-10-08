package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.DeviceAbnormalLog;
import com.zhilianhua.ssicp.entity.response.DeviceAbnormalLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeviceAbnormalLogMapper {

    List<DeviceAbnormalLogVO> searchByeid(@Param("id") String id);

    void update(DeviceAbnormalLog deviceAbnormalLog);

    void add(DeviceAbnormalLog deviceAbnormalLog);
}