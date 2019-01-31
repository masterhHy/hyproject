package com.hao.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.common.constant.DataBaseConstant;
import com.hao.common.entity.user.SysUser;
import com.hao.common.entity.user.SysUserRoles;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysUserQuery;
import com.hao.common.utils.UUID;
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


}
