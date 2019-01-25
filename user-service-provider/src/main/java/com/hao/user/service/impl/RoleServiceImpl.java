package com.hao.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.common.entity.user.OauthClientDetails;
import com.hao.common.entity.user.SysRole;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysRoleQuery;
import com.hao.user.dao.SysRoleMapper;
import com.hao.user.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Override
	public List<SysRole> getRoleByUserId(String userId) {
		// TODO Auto-generated method stub
		return sysRoleMapper.getRoleByUserId(userId);
	}

	@Override
	public TableData<SysRole> getRoleData(SysRoleQuery query) {
		PageHelper.startPage(query.getPageNumber(),query.getPageSize());
		Example record = new Example(SysRole.class);
		if(StringUtils.isNotBlank(query.getName())){
			record.createCriteria().andLike("name","%"+query.getName()+"%");
		}
		PageInfo<SysRole> page = new PageInfo<>(mapper.selectByExample(record));
		TableData<SysRole> table = new TableData<>();
		table.setTotal(Integer.parseInt(page.getTotal()+""));
		table.setRows(page.getList());
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


}
