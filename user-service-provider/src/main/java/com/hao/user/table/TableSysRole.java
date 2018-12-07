package com.hao.user.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "sys_role")
public class TableSysRole {
    @Id
    private String id;

    @Column(columnDefinition="varchar(255) COMMENT '创建人'")
    private String createdBy;

    @Column(columnDefinition="datetime COMMENT '创建时间'")
    private Date createdDate;

    @Column(columnDefinition="varchar(255) COMMENT '修改人'")
    private String lastModifiedBy;

    @Column(columnDefinition="datetime COMMENT '修改时间'")
    private Date lastModifiedDate;

    @Column(columnDefinition="varchar(255) COMMENT '角色名字'")
    private String name;

    @Column(columnDefinition="varchar(255) COMMENT '角色编码'")
    private String code;
    @Column(columnDefinition="varchar(10) COMMENT 'Y 启用 N禁用'")
    private String isEnable;


    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return created_date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return last_modified_by
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return last_modified_date
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * 获取角色名字
     *
     * @return name - 角色名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名字
     *
     * @param name 角色名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色编码
     *
     * @return code - 角色编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色编码
     *
     * @param code 角色编码
     */
    public void setCode(String code) {
        this.code = code;
    }
}