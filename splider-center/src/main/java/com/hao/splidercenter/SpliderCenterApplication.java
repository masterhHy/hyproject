package com.hao.splidercenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.hao.splidercenter.dao.DictionaryDao;
import com.hao.splidercenter.domain.CodeDayDataSplider;
import com.hao.splidercenter.domain.IndustrySplider;
import com.hao.splidercenter.domain.NetLandCheckSplider;
import com.hao.splidercenter.domain.StockCodeDetailSplider;
import com.hao.splidercenter.domain.StockCodeInvestorSplider;
import com.hao.splidercenter.domain.StockCodeSplider;
import com.hao.splidercenter.po.Dictionary;


@SpringBootApplication
public class SpliderCenterApplication {
   public static void main(String[] args){
	   SpringApplication.run(SpliderCenterApplication.class, args);
   }
   
   
   @Component
   class Start implements CommandLineRunner {
	   @Autowired
	   private StockCodeSplider stockCodeSplider;
	   @Autowired
	   private StockCodeInvestorSplider stockCodeInvestorSplider;
	   @Autowired
	   private IndustrySplider industrySplider;
	   @Autowired
	   private StockCodeDetailSplider stockCodeDetailSplider;
	   @Autowired
	   private CodeDayDataSplider codeDayDataSplider;
	   @Autowired
	   private NetLandCheckSplider netLandCheckSplider;
	   
	@Override
	public void run(String... args) throws Exception {
		
		//更新股票代码（已有则不更新 判断依据：code）
		
		stockCodeSplider.run();
		
		//更新股票 行业（已有则不更新 判断依据：stock_code表 industry字段not null）
		
		industrySplider.run();
		
		
		//更新股票投资者持股量 （已有则不更新 判断依据：表中codeId 和 日期 存在 ）
		
		//stockCodeInvestorSplider.run();
		
		//更新股票基本面分析（已有则不更新 判断依据：表中codeId 和 日期 存在 ）输入 日期
		
		stockCodeDetailSplider.run();
		
		//更新股票每日盘中数据 （已有则不更新 判断依据：表中codeId 和 时间段  存在 ）输入 日期
		
		//codeDayDataSplider.run();
		
		
		
	}
	   
   }
   
}
