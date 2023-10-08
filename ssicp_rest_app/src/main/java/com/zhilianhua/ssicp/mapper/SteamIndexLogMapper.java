package com.zhilianhua.ssicp.mapper;


import com.zhilianhua.ssicp.entity.db.SteamIndexLog;

import java.util.List;

public interface SteamIndexLogMapper {
    List<SteamIndexLog> findLast();

    void add(SteamIndexLog steamIndexLog);

    List<SteamIndexLog> findToday();
}
