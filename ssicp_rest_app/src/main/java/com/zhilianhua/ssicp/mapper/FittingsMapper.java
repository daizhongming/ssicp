package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.Fittings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管件表 Mapper 接口
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface FittingsMapper {

    List<Fittings> selectbypid(@Param("id") String id);

    void saveBatch(List<Fittings> fittings);

    void deleteBypid(@Param("pid") String pid);
}
