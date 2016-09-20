package com.ynnjii.common;

/**
 * @author yzh
 * Created on 2016/9/20.
 */
public class CommonVo {
    private Integer pageNumber = 1; // 当前页
    private Integer pageIndex;  // 当前页起始位置
    private Integer pageSize = 10; // 每页数量

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageNumber * pageSize - pageSize;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
