package com.zhilianhua.ssicp.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonVo {
    private String buttonId;
    private Boolean isPermission;
}
