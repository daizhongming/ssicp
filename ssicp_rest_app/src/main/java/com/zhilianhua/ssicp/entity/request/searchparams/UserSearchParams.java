package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchParams extends PageParams {
    private String nickName;
}
