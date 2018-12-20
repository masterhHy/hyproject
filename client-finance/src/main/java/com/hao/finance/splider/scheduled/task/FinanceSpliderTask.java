package com.hao.finance.splider.scheduled.task;

import com.hao.finance.splider.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 金融项目数据爬取 任务类
 *
 */
@Component
public class FinanceSpliderTask {
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
	   public void run(){
		   //更新股票代码（已有则不更新 判断依据：code）
			stockCodeSplider.run();
			
			//更新股票 行业（已有则不更新 判断依据：stock_code表 industry字段not null）
		   industrySplider.run();
			
			
			//更新股票投资者持股量 （已有则不更新 判断依据：表中codeId 和 日期 存在 ）
			stockCodeInvestorSplider.run();
			
			//更新股票基本面分析（已有则不更新 判断依据：表中codeId 和 日期 存在 ）输入 日期
			stockCodeDetailSplider.run();
			
			//更新股票每日盘中数据 （已有则不更新 判断依据：表中codeId 和 时间段  存在 ）输入 日期
			codeDayDataSplider.run();
	   }
}
