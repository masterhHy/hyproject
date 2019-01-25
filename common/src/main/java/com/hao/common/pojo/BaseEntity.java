package com.hao.common.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Column(columnDefinition="datetime COMMENT '修改时间'")
    private Date updateTime;
    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
