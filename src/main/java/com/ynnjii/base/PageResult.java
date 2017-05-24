package com.ynnjii.base;

import java.util.List;

/**
 * @author yzh
 * Created on 2016/9/20.
 */
public class PageResult {
    private int total;
    private List rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
