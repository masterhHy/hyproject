package com.hao.user.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "sys_role_authorities")
public class SysRoleAuthorities implements Serializable {
    @Column(name = "sys_role_id")
    private String sysRoleId;

    @Column(name = "authorities_id")
    private String authoritiesId;

    /**
     * @return sys_role_id
     */
    public String getSysRoleId() {
        return sysRoleId;
    }

    /**
     * @param sysRoleId
     */
    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    /**
     * @return authorities_id
     */
    public String getAuthoritiesId() {
        return authoritiesId;
    }

    /**
     * @param authoritiesId
     */
    public void setAuthoritiesId(String authoritiesId) {
        this.authoritiesId = authoritiesId;
    }
}