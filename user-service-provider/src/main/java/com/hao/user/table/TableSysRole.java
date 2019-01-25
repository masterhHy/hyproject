package com.hao.user.table;

import com.hao.common.pojo.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sys_role")
public class TableSysRole extends BaseEntity {
    @Id
    private String id;

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