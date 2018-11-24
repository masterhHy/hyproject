package com.hao.finance.controller;

import com.github.pagehelper.PageInfo;
import com.hao.common.pojo.ResponseData;
import com.hao.finance.entity.SpliderInvestor;
import com.hao.finance.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investor")
public class InvestorController {

	@Autowired
	private InvestorService investorService;
	
	@RequestMapping("/{id}")
	public ResponseData<SpliderInvestor> getInvestor(@PathVariable("id")String id){
		ResponseData<SpliderInvestor> res = new ResponseData<>();
		PageInfo<SpliderInvestor> selectAllByList = investorService.selectAllByList(0, 5);
		res.setCode(200);
		res.setData(selectAllByList.getList().get(0));
		return res;
		
	}
	
	
	
	
}
