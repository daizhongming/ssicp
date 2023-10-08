package com.zhilianhua.ssicp.entity.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResultEntity<T> {
    private long total;
    private List<T> data;
    public static PageResultEntity EMPTY = new PageResultEntity(0L, Collections.emptyList());

    public static PageResultEntity build() {
        return new PageResultEntity();
    }

    public PageResultEntity total(long total) {
        this.total = total;
        return this;
    }

    public PageResultEntity data(List<T> data) {
        this.data = data;
        return this;
    }
}
