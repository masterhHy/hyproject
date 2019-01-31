package com.hao.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysRoleAuthorities;
import com.hao.common.entity.user.SysUser;
import com.hao.common.entity.user.SysUserRoles;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysRoleQuery;
import com.hao.common.utils.UUID;
import com.hao.user.dao.SysAuthorityMapper;
import com.hao.user.dao.SysRoleAuthoritiesMapper;
import com.hao.user.dao.SysRoleMapper;
import com.hao.user.dao.SysUserMapper;
import com.hao.user.dao.SysUserRolesMapper;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleAuthoritiesMapper sysRoleAuthoritiesMapper;
	@Autowired
	private SysUserRolesMapper sysUserRolesMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private UserService userService;
	@Autowired
    private SysAuthorityMapper sysAuthorityMapper;
	
	
	@Override
	public List<SysRole> getRoleByUserId(String userId) {
		// TODO Auto-generated method stub
		return sysRoleMapper.getRoleByUserId(userId);
	}

	@Override
	public TableData<Map<String,Object>> getRoleData(SysRoleQuery query) {
		PageHelper.startPage(query.getPageNumber(),query.getPageSize());
		Example record = new Example(SysRole.class);
		if(StringUtils.isNotBlank(query.getName())){
			record.createCriteria().andLike("name","%"+query.getName()+"%");
		}
		PageInfo<SysRole> page = new PageInfo<>(mapper.selectByExample(record));
		List<SysRole> list = page.getList();
		List<Map<String,Object>> res = new ArrayList<>();
		for (SysRole sysRole : list) {
			JSONObject json = (JSONObject) JSONObject.toJSON(sysRole);
			Map<String,Object> item =json.toJavaObject(json, Map.class);
			item.put("auth", sysAuthorityMapper.getRoleAllAuthority(sysRole.getId()));
			res.add(item);
		}
		TableData<Map<String,Object>> table = new TableData<>();
		table.setTotal(Integer.parseInt(page.getTotal()+""));
		table.setRows(res);
		return table;
	}

	@PostConstruct
	public void initSysRole() {
		SysRole baseRole = new SysRole();
		baseRole.setId("1");
		baseRole =sysRoleMapper.selectOne(baseRole);
		if(baseRole==null){
			baseRole = new SysRole();
			baseRole.setCreateTime(new Date());
			baseRole.setId("1");
			baseRole.setIsEnable("Y");
			baseRole.setName("基础角色");
			baseRole.setCode("BASE_ROLE");
			sysRoleMapper.insertSelective(baseRole);
		}
	}

	@Override
	public void addOrUpdateRole(SysRole role,String auth) {
		SysRole save =null;
		if(StringUtils.isNotBlank(role.getId())){
			save = sysRoleMapper.selectByPrimaryKey(role.getId());
			save.setIsEnable(role.getIsEnable());
			save.setName(role.getName());
			save.setUpdateTime(new Date());
			sysRoleMapper.updateByPrimaryKeySelective(save);
			userService.refreshRedisUser();
		}else{
			save=role;
			save.setId(UUID.uuid32());
			save.setCreateTime(new Date());
			save.setCode(sysRoleMapper.getMaxCode().get("lastNum").toString());
			sysRoleMapper.insertSelective(save);
		}
		//维护该角色下的权限
		//删除 该角色下的所有权限
		Example record = new Example(SysRoleAuthorities.class);
		record.createCriteria().andEqualTo("sysRoleId", save.getId());
		sysRoleAuthoritiesMapper.deleteByExample(record);
		if(StringUtils.isNotBlank(auth)){
			// 把权限添加上去
			String[] authes = auth.split(",");
			for (String authId : authes) {
				SysRoleAuthorities ra = new SysRoleAuthorities();
				ra.setCreateTime(new Date());
				ra.setAuthoritiesId(authId);
				ra.setSysRoleId(save.getId());
				ra.setId(UUID.uuid32());
				sysRoleAuthoritiesMapper.insertSelective(ra);
			}
			
		}
		
		
		
	}

	@Override
	public void deleteRoleById(String id) {
		//删除 该角色下的所有权限
		Example record = new Example(SysRoleAuthorities.class);
		record.createCriteria().andEqualTo("sysRoleId", id);
		sysRoleAuthoritiesMapper.deleteByExample(record);
		
		//删除用户对应的所有角色
		Example example = new Example(SysUserRoles.class);
		example.createCriteria().andEqualTo("rolesId", id);
		sysUserRolesMapper.deleteByExample(example);
		
		
		//删除该角色
		sysRoleMapper.deleteByPrimaryKey(id);
		
		userService.refreshRedisUser();
	}

	@Override
	public TableData<SysUser> getRoleUserData(SysRoleQuery query) {
		PageHelper.startPage(query.getPageNumber(),query.getPageSize());
		if(StringUtils.isNotBlank(query.getUsername())){
			query.setUsername("%"+query.getUsername()+"%");
		}else{
			query.setUsername(null);
		}
		PageInfo<SysUser> page = new PageInfo<>(sysUserMapper.getRoleUserData(query.getId(), query.getHisUser(),query.getUsername()));
		List<SysUser> list = page.getList();
		for (SysUser sysUser : list) {
			sysUser.setPassword(null);
		}
		TableData<SysUser> table = new TableData<>();
		table.setTotal(Integer.parseInt(page.getTotal()+""));
		table.setRows(list);
		return table;
	}

	@Override
	public void addUserToRole(String roleId,String userIds) {
		if(StringUtils.isNotBlank(userIds)){
			List<String> list = Arrays.asList(userIds.split(","));
			for (String userId : list) {
				SysUserRoles record = new SysUserRoles();
				record.setRolesId(roleId);
				record.setSysUserId(userId);
				List<SysUserRoles> select = sysUserRolesMapper.select(record);
				if(select.size()==0){
					record.setCreateTime(new Date());
					record.setId(UUID.uuid32());
					sysUserRolesMapper.insertSelective(record);
				}
			}
		}
		
	}

	@Override
	public void deleteUserToRole(String roleId,String userIds) {
		if(StringUtils.isNotBlank(userIds)){
			List<String> list = Arrays.asList(userIds.split(","));
			for (String userId : list) {
				SysUserRoles record = new SysUserRoles();
				record.setRolesId(roleId);
				record.setSysUserId(userId);
				sysUserRolesMapper.delete(record);
			}
		}
		
	}


}
