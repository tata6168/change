package com.tata.change.base.demo;


public class Query<T extends Demo>{
    //当前页
    private Long currentPage = null;
    //每页数据量
    private Long pageSize = null;
    private Long buf;


    public Long getCurrentPage() {
        return currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setCurrentPage(Long currentPage) {
        if(currentPage == null){
            this.currentPage = 1L;
        }else if(currentPage <= 0){
            this.currentPage = 1L;
        }else {
            this.currentPage = currentPage;
        }
        if(buf!=null){
            this.currentPage = (this.currentPage-1)*buf;
        }else {
            this.buf=this.currentPage;
        }
    }

    public void setPageSize(Long pageSize) {
        if(pageSize==null){
            this.pageSize = 10L;
        }else if(pageSize <= 0){
            this.pageSize = 10L;
        }else {
            this.pageSize = pageSize;
        }
        if(buf!=null){
            this.currentPage = (buf-1)*this.pageSize;
        }else {
            this.buf=this.pageSize;
        }
    }
}
