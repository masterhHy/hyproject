package com.hao.user.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.common.constant.DataBaseConstant;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.entity.user.SysUserRoles;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysUserQuery;
import com.hao.common.utils.UUID;
import com.hao.user.dao.SysRoleMapper;
import com.hao.user.dao.SysUserMapper;
import com.hao.user.dao.SysUserRolesMapper;
import com.hao.user.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserRolesMapper sysUserRolesMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostConstruct
	public void initSysUser() {
		SysUser admin = new SysUser();
		admin.setId("1");
		admin =userMapper.selectOne(admin);
		if(admin==null){
			admin = new SysUser();
			admin.setCreateTime(new Date());
			admin.setFirstName("管理员");
			admin.setId("1");
			admin.setIsEnable("Y");
			admin.setSex(0);
			admin.setUsername("admin");
			admin.setPhone("10086");
			admin.setRegisterSource(0);
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder(6);
			admin.setPassword(bc.encode("84992361"));
			userMapper.insertSelective(admin);
		}

		
	}

	public void registUser(SysUser user) {
		//注册用户
		if(StringUtils.isBlank(user.getId())){
			user.setId(UUID.uuid32());
		}
		user.setCreateTime(new Date());
		user.setIsEnable("Y");
		user.setPhone(user.getUsername());
		if(StringUtils.isBlank(user.getFirstName())){
			user.setFirstName(UUID.dealPhone(user.getUsername()));
		}
		userMapper.insertSelective(user);
		//添加基础角色
		SysUserRoles ur = new SysUserRoles();
		ur.setId(UUID.uuid32());
		ur.setRolesId("1");
		ur.setSysUserId(user.getId());
		ur.setCreateTime(new Date());
		sysUserRolesMapper.insert(ur);


	}

	@Override
	public void refreshRedisUser() {
		String keyStr =DataBaseConstant.REDIS_USER_NAME_PLACE+"*-user";
		Set<String> keys = redisTemplate.keys(keyStr);
		String adminKey =DataBaseConstant.REDIS_USER_NAME_PLACE+"1-user";
		keys.remove(adminKey);
        if (keys.size()>0) {
            redisTemplate.delete(keys);
        }
	}

	@Override
	public TableData<SysUser> getUserData(SysUserQuery query) {
		PageHelper.startPage(query.getPageNumber(),query.getPageSize());
		Example record = new Example(SysUser.class);
		if(StringUtils.isNotBlank(query.getUsername())){
			record.createCriteria().andLike("username","%"+query.getUsername()+"%");
		}
		PageInfo<SysUser> page = new PageInfo<>(mapper.selectByExample(record));
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
	public TableData<SysRole> getUserRoleData(SysUserQuery query) {
		PageHelper.startPage(query.getPageNumber(),query.getPageSize());
		if(StringUtils.isNotBlank(query.getName())){
			query.setName("%"+query.getName()+"%");
		}else{
			query.setName(null);
		}
		PageInfo<SysRole> page = new PageInfo<>(sysRoleMapper.getUserRoleData(query.getId(), query.getHisRole(),query.getName()));
		TableData<SysRole> table = new TableData<>();
		table.setTotal(Integer.parseInt(page.getTotal()+""));
		table.setRows(page.getList());
		return table;
	}

	@Override
	public void deleteUserById(SysUserQuery query) {
		// 删除该用户下的所有角色
		Example ex = new Example(SysUserRoles.class);
		ex.and().andEqualTo("sysUserId", query.getId());
		sysUserRolesMapper.deleteByExample(ex);
		//删除该用户
		userMapper.deleteByPrimaryKey(query.getId());
		
	}

	@Override
	public void addOrUpdateUser(SysUser user) {
		SysUser save=null;
		if(StringUtils.isNotBlank(user.getId())){
			//修改
			save = userMapper.selectByPrimaryKey(user.getId());
			save.setUpdateTime(new Date());
			save.setIsEnable(user.getIsEnable());
			save.setFirstName(user.getFirstName());
			save.setSex(user.getSex());
			userMapper.updateByPrimaryKeySelective(save);
		}else{
			//新增
			save=user;
			save.setCreateTime(new Date());
			save.setRegisterSource(0);
			save.setId(UUID.uuid32());
			String password = save.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder(6);
			save.setPassword(bc.encode(password));
			userMapper.insert(save);
		}
		
	}

	@Override
	public void addRoleoUser(SysUserQuery query) {
		if(StringUtils.isNotBlank(query.getRoleIds())){
			List<String> list = Arrays.asList(query.getRoleIds().split(","));
			for (String roleId : list) {
				SysUserRoles record = new SysUserRoles();
				record.setRolesId(roleId);
				record.setSysUserId(query.getId());
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
	public void deleteRoleFromUser(SysUserQuery query) {
		if(StringUtils.isNotBlank(query.getRoleIds())){
			List<String> list = Arrays.asList(query.getRoleIds().split(","));
			for (String roleId : list) {
				SysUserRoles record = new SysUserRoles();
				record.setSysUserId(query.getId());
				record.setRolesId(roleId);
				sysUserRolesMapper.delete(record);
			}
		}
		
	}


}
