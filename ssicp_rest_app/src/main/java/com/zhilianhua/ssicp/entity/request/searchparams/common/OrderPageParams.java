package com.zhilianhua.ssicp.entity.request.searchparams.common;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import com.zhilianhua.ssicp.exception.BusinessException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class OrderPageParams extends PageParams {
    private static final String ORDER_DESC_STRING = "descending";
    private static final String ORDER_DESC_SQL = "desc";
    private static final String ORDER_ASC_STRING = "ascending";
    private static final String ORDER_ASC_SQL = "asc";
    private String order;
    private String prop;

    public String getOrder() {
        if (StringUtils.isNoneBlank(order)) {
            if (StringUtils.equals(ORDER_DESC_STRING, order)) return ORDER_DESC_SQL;
            else if (StringUtils.equals(ORDER_ASC_STRING, order)) return ORDER_ASC_SQL;
        }
        return null;
    }

    public OrderPageParams() {
        propsMap = setPropsMap();
    }

    private Map<String, String> propsMap = new HashMap();

    public abstract Map<String, String> setPropsMap();

    public String getProp() {
        if (StringUtils.isBlank(getOrder())) return null;
        if (StringUtils.isBlank(prop)) throw BusinessException.instance("排序字段为空");
        if (!propsMap.containsKey(prop)) BusinessException.instance("排序字段映射关系后端没有配置");
        return propsMap.get(prop);
    }

    public static Map<String, String> mapBuilder(String... args) {
        if (args.length == 0) throw BusinessException.instance("排序字段映射关系设置有误");
        if (args.length % 2 != 0) throw BusinessException.instance("排序字段映射关系设置有误");
        Map<String, String> map = new HashMap();
        for (int i = 0; i < args.length; i += 2) {
            String key = args[i];
            String value = args[i + 1];
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value))
                throw BusinessException.instance("排序字段映射关系设置有误");
            map.put(key, value);
        }
        return map;
    }
}
