package com.hao.splidercenter.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hao.splidercenter.scheduled.task.FinanceSpliderTask;
@Component
public class ScheduledMain {
	@Autowired
	private FinanceSpliderTask financeSpliderTask;
	/*
	1  秒（0~59）
	2 分钟（0~59）
	3 小时（0~23）
	4 天（0~31）
	5 月（0~11）
	6 星期（ SUN，MON，TUE，WED，THU，FRI，SAT）
	年份（1970－2099）
	每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符
	
	每隔5秒执行一次：/5 * * * ?
	0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
	
	'?' 只适用与天 和星期，若有天 则可以不指定星期 星期可以?
	/表示 每隔多少执行
	*/
	
	@Scheduled(cron="0 0 3 ? * SUN ")//每个星期天凌晨三点执行
	@Async
	public void financeRun(){
		financeSpliderTask.run();
	}
	
	
}
