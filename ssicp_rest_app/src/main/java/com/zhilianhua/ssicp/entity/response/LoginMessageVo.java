package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoginMessageVo {
    private List<String> menuCodeList = new ArrayList();
    private List<String> buttonIdList = new ArrayList();
    private UserVo userVo;
}
