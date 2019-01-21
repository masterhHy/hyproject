package com.hao.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hao.common.entity.user.OauthClientDetails;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.OauthClientDetailsQuery;
import com.hao.user.dao.OauthClientDetailsMapper;
import com.hao.user.service.ClientService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl extends BaseServiceImpl<OauthClientDetails> implements ClientService {
    @Autowired
    private OauthClientDetailsMapper mapper;

    @Override
    public TableData<OauthClientDetails> getClientData(OauthClientDetailsQuery query) {
        PageHelper.startPage(query.getPageNumber(),query.getPageSize());
        Example record = new Example(OauthClientDetails.class);
        if(StringUtils.isNotBlank(query.getClientId())){
            record.createCriteria().andLike("clientId","%"+query.getClientId()+"%");
        }
        PageInfo<OauthClientDetails> page = new PageInfo<>(mapper.selectByExample(record));
        TableData<OauthClientDetails> table = new TableData<>();
        List<OauthClientDetails> list = page.getList();
        for (OauthClientDetails details:list){
            if(StringUtils.isNotBlank(details.getAuthorizedGrantTypes())){
                List<String> arr = Arrays.asList(details.getAuthorizedGrantTypes().split(","));
                details.setAuthorizedGrantTypesArr(arr);
            }
        }
        table.setRows(list);
        table.setTotal(Integer.parseInt(page.getTotal()+""));
        return table;
    }
}
