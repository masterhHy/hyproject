package com.hao.user.service.impl;

import com.hao.common.entity.coin.TimeCoin;
import com.hao.common.entity.user.SysAuthority;
import com.hao.user.dao.SysAuthorityMapper;
import com.hao.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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
