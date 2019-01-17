package com.hao.common.pojo;

/**
 * 基础查询参数实体
 */
public class BaseRequestPojo {

    protected Integer pageNumber;

    protected Integer pageSize;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getStartIndex(){
        if(this.pageNumber==null){
            this.pageNumber=1;
        }
        if(this.pageSize==null){
            this.pageSize=10;
        }
        Integer start = (this.pageNumber-1)*this.pageSize;
        return start;
    }
    public Integer getEndIndex(){
        if(this.pageNumber==null){
            this.pageNumber=1;
        }
        if(this.pageSize==null){
            this.pageSize=10;
        }
        Integer end = this.pageNumber*this.pageSize-1;
        return end;
    }

}
