package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Fittings;
import com.zhilianhua.ssicp.mapper.FittingsMapper;
import com.zhilianhua.ssicp.service.FittingsService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管件表 服务实现类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@Service
public class FittingsServiceImpl implements FittingsService {
    @Autowired
    private FittingsMapper fittingsMapper;


    @Override
    public void saveBatch(List<Fittings> fittings) {
        fittingsMapper.saveBatch(fittings);
    }

    @Override
    public void deleteBypid(String id) {
        fittingsMapper.deleteBypid(id);
    }

    @Override
    public List<Fittings> selectbypid(String id) {
        return fittingsMapper.selectbypid(id);
    }
}
