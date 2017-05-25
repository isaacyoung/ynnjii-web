package com.ynnjii.base;

import com.github.pagehelper.Page;

/**
 * project：ynnjii-web
 * class：序列化 分页信息
 * author：yzh
 * date：2017/5/25
 * description：
 */
public class SerializePageInfo {

    private int pageNum;
    private int pageSize;
    private long total;

    public SerializePageInfo(Page page) {
        pageNum=page.getPageNum();
        pageSize=page.getPageSize();
        total=page.getTotal();
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
