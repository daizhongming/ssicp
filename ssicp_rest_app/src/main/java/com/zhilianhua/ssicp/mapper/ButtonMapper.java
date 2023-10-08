package com.zhilianhua.ssicp.mapper;


import com.zhilianhua.ssicp.entity.db.Button;
import com.zhilianhua.ssicp.entity.request.searchparams.ButtonParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ButtonMapper {
    List<Button> findAll();

    PageResultEntity<Button> search(ButtonParams buttonParams);

    void delete(@Param("id") String id);

    Button findByid(@Param("id") String id);

    void add(Button button);

    void update(Button button);
}
