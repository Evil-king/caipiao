package com.caipiao.vo;

public class ResultPagination {
	/**
     * 默认页码
     */
    public static final int DEFAULT_PAGE = 0;
    /**
     * 默认每页限制大小
     */
    public static final int DEFAULT_ROWS = 10;
    /**
     * the page number
     */
    private int page;
    /**
     * the page size
     */
    private int rows;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if (page > 1) {
            this.page = (page - 1) * rows;
        } else {
            this.page = 0;
        }
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
}
