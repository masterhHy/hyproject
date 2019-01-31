package com.hao.time.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.hao.common.pojo.BaseEntity;

@Entity(name="time_dream")
@Table(name="time_dream",indexes={@Index(name="time_dream_userid_ix",columnList="userId")})
public class TableTimeDream extends BaseEntity {
    @Id
    private String id;
    @Column(columnDefinition="double COMMENT '梦想基金钱 '")
    private Double price;
    
    @Column(columnDefinition="varchar(255) COMMENT '标题'")
    private String title;
    @Column(columnDefinition="text COMMENT '描述'")
    private String description;
    @Column(columnDefinition="int(9) COMMENT '权重'")
    private Integer priority;
    @Column(columnDefinition="varchar(255) COMMENT '用户表id'")
    private String userId;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

    
    

}