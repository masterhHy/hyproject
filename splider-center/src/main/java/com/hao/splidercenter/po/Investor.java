package com.hao.splidercenter.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="splider_investor")
public class Investor implements ParentPo {
	@Id
	private String id;
	@Column(columnDefinition="varchar(255) COMMENT '投资公司'")
	private String name;
	@Column(columnDefinition="int(3) COMMENT '公司类型 1:基金	2:保险公司		3:一般法人		4:信托公司		5:社保基金		6:QFII 	7:券商	8:券商集合理财		9:企业年金'")
	private Integer type;
	@Column(columnDefinition="datetime COMMENT '创建时间' ")
	private Date zCreatTime;
	@Column(columnDefinition="datetime COMMENT '修改时间' ")
	private Date zUpdateTime;
	
	public static final Integer TYPE_FUND=1;
	public static final Integer TYPE_SAFE=2;
	public static final Integer TYPE_PERSON=3;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public Date getzCreatTime() {
		return zCreatTime;
	}
	public void setzCreatTime(Date zCreatTime) {
		this.zCreatTime = zCreatTime;
	}
	public Date getzUpdateTime() {
		return zUpdateTime;
	}
	public void setzUpdateTime(Date zUpdateTime) {
		this.zUpdateTime = zUpdateTime;
	}
	@Override
	public String toString() {
		return "Inventor [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	@Override
	public String toWritingString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
