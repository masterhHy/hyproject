package com.hao.user.entity.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "sys_user_roles")
public class TableSysUserRoles implements Serializable {
    @Column(columnDefinition="varchar(255) COMMENT '用户表id'")
    private String sysUserId;

    @Column(columnDefinition="varchar(255) COMMENT '角色表id'")
    private String rolesId;
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return sys_user_id
     */
    public String getSysUserId() {
        return sysUserId;
    }

    /**
     * @param sysUserId
     */
    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * @return roles_id
     */
    public String getRolesId() {
        return rolesId;
    }

    /**
     * @param rolesId
     */
    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }
}