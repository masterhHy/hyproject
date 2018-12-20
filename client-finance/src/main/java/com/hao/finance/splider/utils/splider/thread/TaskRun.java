package com.hao.finance.splider.utils.splider.thread;

import com.hao.finance.splider.utils.splider.Splider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多连接爬虫，使用多线程
 * @author Administrator
 *
 */
public class TaskRun implements Runnable{
	private Logger logger = LoggerFactory.getLogger(TaskRun.class);
	private SpliderTaskVo task;
	private Monitor monitor;
	private HttpConnectionManager pools;
	private Boolean userProxy=false;

	public TaskRun(SpliderTaskVo task, Monitor monitor) {
		this.task=task;
		this.monitor=monitor;
	}
	public void setPools(HttpConnectionManager pools) {
		this.pools = pools;
		if(pools!=null&&pools.getUseProxy()){
			userProxy=true;
		}
	}

	@Override
	public void run() {
		try {
			Splider s = new Splider(task.getHandler());
			s.unicode=task.getUnicode();
			s.setPools(pools);
			s.readUrl(task.getUrl(), task.getType(), task.getParams());
			if(userProxy){
				Thread.currentThread().sleep(500);
			}else{
				Thread.currentThread().sleep(1000);
			}
			synchronized (monitor) {
				monitor.currendURL(task.getUrl());
			}

		} catch (Exception e) {
			logger.error("爬虫出错--->连接"+task.getUrl());
			synchronized (monitor) {
				monitor.currendURL(task.getUrl());
			}
			monitor.errorList.add(task);
		}

	}




	/*************静态方法 与该类无关********************************************/

	public static void run(List<SpliderTaskVo> tasks){
		Response r = new Response();
		run(tasks,r,null);
	}
	public static void run(List<SpliderTaskVo> tasks, Monitor monitor){
		run(tasks,monitor,null);
	}

	public static void run(List<SpliderTaskVo> tasks, HttpConnectionManager pools){
		Response r = new Response();
		run(tasks,r,pools);
	}

	public static void run(List<SpliderTaskVo> tasks, Monitor monitor, HttpConnectionManager pools){
		int maxThreads =5;
		if(pools==null||!pools.getUseProxy()){
			maxThreads=1;
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
		for (SpliderTaskVo task : tasks) {
			TaskRun r = new TaskRun(task,monitor);
			r.setPools(pools);
			executor.submit(r);
		}
		
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(1, TimeUnit.DAYS);
		}catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    monitor.end(monitor.errorList);
		    System.out.println("shutdown finished");
		}
		
	}
	

	
	
	
	
	
	
	
}

class Response implements Monitor {

	@Override
	public void currendURL(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(List<SpliderTaskVo> error) {
		// TODO Auto-generated method stub
		
	}
}