package com.hao.splidercenter.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="splider_dictionary")
public class Dictionary  {
	@Id
	private String id;
	@Column(columnDefinition="varchar(50) COMMENT '用来关联code值' ")
	private String code;
	@Column(columnDefinition="varchar(50) COMMENT '分类表示' ")
	private String sort;
	public static final String SORT_INDUSTRY="INDUSTRY";
	
	@Column(columnDefinition="varchar(50) COMMENT '名字' ")
	private String name;
	@Column(columnDefinition="varchar(50) COMMENT '父类id 若为null则为根' ")
	private String parentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", code=" + code + ", sort=" + sort + ", name=" + name + ", parentId="
				+ parentId + "]";
	}
	
}
