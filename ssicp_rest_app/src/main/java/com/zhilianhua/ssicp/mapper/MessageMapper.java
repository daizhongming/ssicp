package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.Message;
import com.zhilianhua.ssicp.entity.response.MessageAnalyseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    void add(Message message);

    List<Message> findByUser(@Param("userName") String userName);

    void readAll(String userName);

    void readById(Long id);

    Message load(@Param("type") String type, @Param("businessId") String businessId, @Param("userName") String userName);

    int update(Message message);

    List<MessageAnalyseEntity> analyse(@Param("userName")String userName);

    void readByTypeAndBusinessId(@Param("type")String type, @Param("businessId")String businessId);
}