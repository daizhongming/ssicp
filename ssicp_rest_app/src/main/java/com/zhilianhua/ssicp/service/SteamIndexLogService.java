package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.SteamIndexLog;

import java.util.List;

public interface SteamIndexLogService {
    List<SteamIndexLog> findToday();
}
