package com.hao.time.table;

import javax.persistence.*;
import java.util.Date;

@Entity(name="time_coin")
@Table(name="time_coin",indexes={@Index(name="time_markday_ix",columnList="markDay")})
public class TableTimeCoin {
    @Id
    private String id;
    @Column(columnDefinition="int(3) COMMENT '金币类型: 1 工作 2 学习 3 娱乐 '")
    private Integer type;
    @Column(columnDefinition="text COMMENT '金币含义'")
    private String remark;
    @Column(columnDefinition="varchar(255) COMMENT '金币记录天'")
    private String markDay;
    @Column(columnDefinition="varchar(255) COMMENT '用户表id'")
    private String userId;

    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date createTime;
    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date updateTime;
    
    @Column(columnDefinition="int(2) COMMENT '是否删除	0:否	1:删除'")
    private Integer isDelete;
    
    
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMarkDay() {
        return markDay;
    }

    public void setMarkDay(String markDay) {
        this.markDay = markDay;
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