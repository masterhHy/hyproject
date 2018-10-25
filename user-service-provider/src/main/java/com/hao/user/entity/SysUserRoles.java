package com.hao.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sys_user_roles")
public class SysUserRoles implements Serializable {
    @Id
    private String id;
    @Column(name = "sys_user_id")
    private String sysUserId;

    @Column(name = "roles_id")
    private String rolesId;

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