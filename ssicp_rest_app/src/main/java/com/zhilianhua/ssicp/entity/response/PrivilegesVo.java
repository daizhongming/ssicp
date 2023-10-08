package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class PrivilegesVo {
    private List<String> menuCodes;
    private List<String> buttonIds;
}
