package com.hao.common.pojo;

import java.util.List;


public class TableData<T> {

    private Integer total;

    private List<T> rows;


    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TableData{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
