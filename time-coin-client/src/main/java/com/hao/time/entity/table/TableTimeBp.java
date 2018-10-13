package com.hao.time.entity.table;

import javax.persistence.*;
import java.util.Date;

@Entity(name="time_bp")
public class TableTimeBp {
    @Id
    private String id;
    @Column(columnDefinition="int(3) COMMENT '积分类型'")
    private Integer type;
    @Column(columnDefinition="int(11) COMMENT '积分总数'")
    private Integer amount;
    @Column(columnDefinition="varchar(255) COMMENT '用户表id'")
    private String userId;
    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date createTime;
    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}