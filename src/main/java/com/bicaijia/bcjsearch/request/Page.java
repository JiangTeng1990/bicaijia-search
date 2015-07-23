package com.bicaijia.bcjsearch.request;

/**
 * daixing， 分页信息。请不要增加其他的内容。
 */
public class Page {

    private Integer curPage = 0;// 当前页数
    private Integer pageRows = 0;// 每页的行数
    private Integer pageAmount = 0;// 总页数

    private Integer startpage = 0;// 开始页
    private Integer endpage = 0;// 结束页
    private Integer totalRows = 0;// 总条数
    private Integer pageAmountShow = 0;// 页面共显示几页

    // ---------------------------------------------------------------

    public Page(Integer curPage, Integer pageRows, Integer totalRows) {
        this.curPage = curPage;
        this.pageRows = pageRows;
        this.totalRows = totalRows;
        if (pageRows > 0) {
            this.pageAmount = (int) (this.totalRows / pageRows);
            if (this.totalRows > this.pageAmount * pageRows) {
                this.pageAmount += 1;
            }
            this.initPage();
        }
    }

    public void initPage() {
        if (null == pageAmountShow) {
            pageAmountShow = 5;
        }
        int half = pageAmountShow / 2;
        startpage = curPage - half;
        if (pageAmount - curPage <= half) {
            startpage = pageAmount - pageAmountShow + 1;
        }
        if (startpage <= 0) {
            startpage = 1;
        }
        endpage = curPage + half;
        if (curPage <= half) {
            endpage = pageAmountShow;
        }
        if (endpage > pageAmount) {
            endpage = pageAmount;
        }
    }

    public Page() {
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageRows() {
        return pageRows;
    }

    public Integer getStartpage() {
        return startpage;
    }

    public void setStartpage(Integer startpage) {
        this.startpage = startpage;
    }

    public Integer getEndpage() {

        return endpage;
    }

    public void setEndpage(Integer endpage) {
        this.endpage = endpage;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getPageAmountShow() {

        return pageAmountShow;
    }

    public void setPageAmountShow(Integer pageAmountShow) {
        this.pageAmountShow = pageAmountShow;
    }

    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

    public Integer getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(Integer pageAmount) {
        this.pageAmount = pageAmount;
    }

}

