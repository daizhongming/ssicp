package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.FileInfo;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {
    void add(FileInfo fileInfo);

    FileInfo get(@Param("code") String code);

    void deleteByCode(@Param("code") String code);
}
