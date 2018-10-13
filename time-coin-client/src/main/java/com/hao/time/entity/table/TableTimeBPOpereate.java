package com.hao.time.entity.table;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="time_bp_operate")
public class TableTimeBPOpereate {
	@Id
	private String id;
	@Column(columnDefinition="varchar(255) COMMENT '积分表id'")
	private String timeBpId;

	@Column(columnDefinition="int(11) COMMENT '积分数'")
	private Integer num;
	@Column(columnDefinition="datetime COMMENT '创建时间'")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeBpId() {
		return timeBpId;
	}

	public void setTimeBpId(String timeBpId) {
		this.timeBpId = timeBpId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
