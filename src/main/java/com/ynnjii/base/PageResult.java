package com.ynnjii.base;

import java.util.List;

/**
 * @author yzh
 * Created on 2016/9/20.
 */
public class PageResult {
    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
