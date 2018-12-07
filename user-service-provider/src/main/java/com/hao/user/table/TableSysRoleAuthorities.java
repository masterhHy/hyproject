package com.hao.user.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sys_role_authorities")
public class TableSysRoleAuthorities  {
    @Id
    private String id;
    @Column(columnDefinition="varchar(255) COMMENT '角色id'")
    private String sysRoleId;

    @Column(columnDefinition="varchar(255) COMMENT '权限id'")
    private String authoritiesId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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