package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Button;
import com.zhilianhua.ssicp.entity.request.searchparams.ButtonParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.mapper.ButtonMapper;
import com.zhilianhua.ssicp.service.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ButtonServiceImpl implements ButtonService {
    @Autowired
    private ButtonMapper buttonMapper;

    @Override
    public List<Button> findAll() {
        return buttonMapper.findAll();
    }


    @Override
    public PageResultEntity<Button> findPage(ButtonParams buttonParams) {
        return buttonMapper.search(buttonParams);
    }

    @Override
    public void delete(String id) {
        buttonMapper.delete(id);
    }

    @Override
    public Button findByid(String id) {
        return buttonMapper.findByid(id);
    }

    @Override
    public void add(Button button) {
        Button byid = findByid(button.getId());
        if (Objects.nonNull(byid)) {
            throw BusinessException.instance("编码已存在");
        }
        buttonMapper.add(button);
    }

    @Override
    public void update(Button button) {
        buttonMapper.update(button);
    }
}
