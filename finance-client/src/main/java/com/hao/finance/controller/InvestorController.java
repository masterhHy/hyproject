package com.hao.finance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hao.common.pojo.ResponseData;
import com.hao.finance.entity.SpliderInvestor;
import com.hao.finance.service.InvestorService;

@Controller
@RequestMapping("/investor")
public class InvestorController {
	@Autowired
	private InvestorService investorService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public ResponseData<SpliderInvestor> getInvestor(@PathVariable("id")String id){
		ResponseData<SpliderInvestor> res = new ResponseData<>();
		PageInfo<SpliderInvestor> selectAllByList = investorService.selectAllByList(0, 5);
		res.setCode(200);
		res.setData(selectAllByList.getList().get(0));
		int i=1/0;
		return res;
		
	}
	
	
	
	
}
