package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class TreeVo {
    List<Entry> entryList;
    @Data
    public static
    class Entry {
        private String id;
        private String title;
        private Object extra;
        private List<Entry> children;
    }
}
