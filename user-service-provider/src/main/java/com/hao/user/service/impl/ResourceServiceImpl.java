package com.hao.user.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.common.entity.user.SysAuthority;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysAuthorityQuery;
import com.hao.common.utils.RecursionUtils;
import com.hao.common.utils.UUID;
import com.hao.user.dao.SysAuthorityMapper;
import com.hao.user.service.ResourceService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<SysAuthority> implements ResourceService {
    @Autowired
    private SysAuthorityMapper mapper;

    @Override
    public List<SysAuthority> getAllAuthorityByUserId(String userId) {
        return mapper.getAllAuthority(userId,null);
    }


    @Override
    public List<Map<String, Object>> getAllMenuByUserId(String userId) {
        //如果是管理员 则获取全部菜单按钮
        List<SysAuthority> menus =null;
        if("1".equals(userId)){
            Example example= new Example(SysAuthority.class);
            example.createCriteria().andEqualTo("type",1);
            menus = this.selectByExample(example);
        }else{
            menus =  mapper.getAllAuthority(userId,1);
        }

        Map<String,Object> projectMap = new HashMap<>();//key 为project value project 下的所有菜单
        for (SysAuthority auth :menus){
            Map<String,Object> item = new HashMap<>();
            item.put("projectName",auth.getProjectName());
            item.put("moduleName",auth.getName());
            item.put("id",auth.getId());
            item.put("modulePath",auth.getUrl());
            item.put("parentId",auth.getParentId());
            item.put("active",1);
            item.put("moduleCode",auth.getSignCode());
            item.put("orderIndex",auth.getOrderNum());
            item.put("moduleIcon",auth.getIcon());
            item.put("subModules",new ArrayList<>());
            if(projectMap.get(auth.getProjectName())==null){
                List<Map<String,Object>> menuList = new ArrayList<>();
                menuList.add(item);
                projectMap.put(auth.getProjectName(),menuList);
            }else{
                List<Map<String,Object>> menuList = (List<Map<String, Object>>) projectMap.get(auth.getProjectName());
                menuList.add(item);
            }

        }

        //递归找他的孩子
        List<Map<String,Object>> res = new ArrayList<>();
        for (String project :projectMap.keySet()){
            List<Map<String,Object>> menuList = (List<Map<String, Object>>) projectMap.get(project);
            Map<String,Object> parent = new HashMap<>();
            for ( Map<String,Object> item : menuList){
                if(item.get("parentId")==null){
                    parent=item;
                    break;
                }
            }
            this.putHisChild(menuList,parent);
            res.add(parent);
        }
        res.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return o1.get("orderIndex").toString().compareTo(o2.get("orderIndex").toString());
            }
        });
        return res;
    }
    protected Logger logger =LoggerFactory.getLogger(this.getClass());
    @Override
    public List<SysAuthority> getAllAuthority()  {
        Example record = new Example(SysAuthority.class);
        record.orderBy("parentId").asc().orderBy("orderNum").asc();
        List<SysAuthority> allAuth = mapper.selectByExample(record);
        RecursionUtils<SysAuthority> r = new RecursionUtils<>();
        try {
        	allAuth =r.formatterHisChild(allAuth, "parentId");
		} catch (Exception e) {
			logger.error("",e);
		}
        return allAuth;
    }

    @Override
    public TableData<SysAuthority> getSubAuthByParentId(SysAuthorityQuery query) {
        TableData<SysAuthority> table = new TableData<>();
        List<SysAuthority> res = new ArrayList<>();
        this.getSubAuthByParentId(res,query.getParentId());
        res.sort(new Comparator<SysAuthority>() {
            @Override
            public int compare(SysAuthority o1, SysAuthority o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });
        List<SysAuthority> rows = new ArrayList<>();
        Integer startIndex =query.getStartIndex();
        Integer endIndex =query.getEndIndex();
        for (int i=0;i<res.size();i++){
            if(i>=startIndex&&i<=endIndex){
                rows.add(res.get(i));
            }
        }
        table.setRows(rows);
        table.setTotal(res.size());

        return table;
    }

    @Override
    public void addOrUpdateAuth(SysAuthority authority) {
        if(StringUtils.isNotBlank(authority.getId())){
            //修改
            authority.setLastModifiedDate(new Date());
            mapper.updateByPrimaryKeySelective(authority);
        }else{
            //新增
            authority.setCreatedDate(new Date());
            authority.setCode( mapper.getMaxCode().get("lastNum").toString());
            authority.setId(UUID.uuid32());
            mapper.insert(authority);
        }
    }

    private void getSubAuthByParentId(List<SysAuthority> tar,String parentId){
        Example record = new Example(SysAuthority.class);
        if(StringUtils.isBlank(parentId)){
        	record.createCriteria().andIsNull("parentId");
        }else{
        	record.createCriteria().andEqualTo("parentId",parentId);
        	
        }
        record.orderBy("projectName").asc().orderBy("parentId").asc().orderBy("orderNum").asc();
        List<SysAuthority> children = mapper.selectByExample(record);
        for (SysAuthority child:children){
            tar.add(child);
            this.getSubAuthByParentId(tar,child.getId());
        }
    }


    private void putHisChild( List<Map<String,Object>> menuList,Map<String,Object> parent){
        for ( Map<String,Object> item : menuList){
            if(parent.get("id").equals(item.get("parentId"))){
                List<Map<String,Object>> subList = (List<Map<String, Object>>) parent.get("subModules");
                subList.add(item);
            }
        }
        List<Map<String,Object>> hisChild = (List<Map<String, Object>>) parent.get("subModules");
        hisChild.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return o1.get("orderIndex").toString().compareTo(o2.get("orderIndex").toString());
            }
        });
        for (Map<String,Object> item :hisChild ){
            this.putHisChild(menuList,item);
        }


    }

}
