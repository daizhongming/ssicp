package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.User;
import lombok.Data;

@Data
public class UserVo extends User {
    private String roleTitle;
    private boolean isInspector;
    private boolean isRoot;
}
