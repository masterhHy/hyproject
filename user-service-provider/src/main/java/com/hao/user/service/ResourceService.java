package com.hao.user.service;


import com.hao.common.entity.user.SysAuthority;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysAuthorityQuery;

import java.util.List;
import java.util.Map;

public interface ResourceService extends BaseService<SysAuthority> {
    public List<SysAuthority> getAllAuthorityByUserId(String userId);
    /**
     * 获取该用户所有菜单权限
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getAllMenuByUserId(String userId);

    /**
     *  获取全部权限，
     */
    public List<SysAuthority> getAllAuthority();

    /**
     * 根据parentId 获取 其下所有权限
     * @param query
     * @return
     */
    public TableData<SysAuthority> getSubAuthByParentId(SysAuthorityQuery query);

    public  void addOrUpdateAuth(SysAuthority authority);

    public void deleteAuthById(String id);
    

}
