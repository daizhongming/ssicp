package com.zhilianhua.ssicp.entity.request.searchparams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuSearchParams {
    private Integer publicFlag;
    private String code;
    private String parentCode;
    private Integer level;
}
