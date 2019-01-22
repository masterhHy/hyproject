package com.hao.user.service;


import com.hao.common.entity.user.OauthClientDetails;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.OauthClientDetailsQuery;

public interface ClientService extends BaseService<OauthClientDetails> {
	public TableData<OauthClientDetails> getClientData(OauthClientDetailsQuery query);
	public  void addOrUpdateClient(OauthClientDetails client);
}
