package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.db.DictType;
import lombok.Data;

import java.util.List;

@Data
public class DictVo extends DictType {
    private List<DictData> dictDataList;
}
