package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.response.LoginMessageVo;

public interface IndexService {
    LoginMessageVo getMessage(String userName);
}
