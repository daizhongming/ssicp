package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Fittings;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <p>
 * 管件表 服务类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface FittingsService {
    void saveBatch(List<Fittings> fittings);

    void deleteBypid(String id);

    List<Fittings> selectbypid(String id);
}
