package com.hao.user.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "sys_user_roles")
public class SysUserRoles implements Serializable {
    @Column(name = "sys_user_id")
    private String sysUserId;

    @Column(name = "roles_id")
    private String rolesId;

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