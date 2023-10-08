package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Button;
import com.zhilianhua.ssicp.entity.request.searchparams.ButtonParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

public interface ButtonService {
    List<Button> findAll();


    PageResultEntity<Button> findPage(ButtonParams buttonParams);

    void delete(String id);

    Button findByid(String id);

    void add(Button button);

    void update(Button button);
}
