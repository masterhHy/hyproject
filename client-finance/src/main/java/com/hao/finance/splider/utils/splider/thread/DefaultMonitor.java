package com.hao.finance.splider.utils.splider.thread;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hao.finance.splider.utils.splider.Splider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 根据泛型 来确定输出目录
 * @author Administrator
 *
 * @param <T>
 */

public abstract class DefaultMonitor<T> implements Monitor {
	protected String splitor =",";
	protected DruidDataSource ds;
	protected Logger logger =LoggerFactory.getLogger(DefaultMonitor.class);
	
	public DefaultMonitor(DruidDataSource ds) {
		this.ds=ds;
		errorList.clear();
	}
	@Override
	public abstract void currendURL(String url);
	
	@Override
	public void end(List<SpliderTaskVo> error) {
		DruidPooledConnection connection =null;
		JSONArray arr = new JSONArray();
		List<SpliderTaskVo> err = new ArrayList<>();
		if(error.size()!=0){
			logger.info("重新爬去错误连接....");
			for (SpliderTaskVo task : error) {
				//再次去爬去连接
				Splider s = new Splider(task.getHandler());
				try {
					s.readUrl(task.getUrl(), task.getType(), task.getParams());
					logger.info(task.getUrl()+"连接爬去成功....");
					err.add(task);
				} catch (Exception e) {
					JSONObject json= new JSONObject();
					json.put("url", task.getUrl());
					json.put("type", task.getType());
					json.put("params", task.getParams());
					json.put("handler", task.getHandler().getClass().getName());
					arr.add(json);
				}
				try {
					Thread.currentThread().sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			error.removeAll(err);
			logger.info("连接reload完毕....");
		}else{
			logger.info("没有报错连接....");
		}
		
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String typeName = entityClass.getTypeName();//泛型的全类名作为文件夹名
		
		//输出错误list 到data下对应的文件夹 名字error.txt 以便后续重新爬去
		try {
			String errorPath ="data/"+typeName.replace(".", "_");
			File errorDir = new File(errorPath);
			errorDir.mkdirs();
			File file = new File(errorPath+"/error.txt");
			OutputStream os = new FileOutputStream(file);
			os.write(arr.toJSONString().getBytes());
			os.close();
		} catch (Exception e) {
			logger.error("输出data目录错误文件出错-->",e);
		}
		
		//输出错误list 到error 对应文件夹 方便查看历史
		try {
			String errorPath ="error/"+typeName.replace(".", "_");
			File errorDir = new File(errorPath);
			errorDir.mkdirs();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String currentDay = sdf.format(new Date());
			
			File file = new File(errorPath+"/error-"+currentDay+".txt");
			//把错误信息输出到txt
			OutputStream os = new FileOutputStream(file);
			for (SpliderTaskVo e : error) {
				String str = e.toGetString()+"\r\n";
				os.write(str.getBytes());
			}
			os.close();
			errorList.clear();
		} catch (Exception e) {
			logger.error("输出error目录错误文件出错-->",e);
		}
		
		try {
			//写完后把数据录入mysql
			String path ="data/"+typeName.replace(".", "_");
			File dir = new File(path);
			dir.mkdirs();
			Class<?> fan = Class.forName(typeName);
			Entity annotation = fan.getAnnotation(Entity.class);
			String tableName = annotation.name();
			StringBuffer sql= new StringBuffer();
			sql.append("LOAD DATA INFILE '?' ")
			   .append("INTO TABLE "+tableName+" ")
			   .append("CHARACTER SET 'utf8' ")
			   .append("FIELDS TERMINATED BY '"+splitor+"' ENCLOSED BY '\"' ")
			   .append("LINES TERMINATED BY '\\r\\n' ");
			connection = ds.getConnection();
			File[] listFiles = dir.listFiles();
			String os = System.getProperty("os.name");
			
			for (File data : listFiles) {
				if(data.getName().equals("error.txt")){
					continue;
				}
				if(os.toLowerCase().indexOf("linux")!=-1){
					//linux要把文件移动到temp目录 不然权限问题不能读取文件
					File tarFile = new File("/temp"+data.getParentFile().getAbsolutePath());
					boolean mkdir = tarFile.mkdirs();
					logger.info("创建文件夹状态:"+mkdir);
					tarFile = new File("/temp"+data.getAbsolutePath());
					boolean renameTo = data.renameTo(tarFile);
					logger.info("移动文件夹状态:"+renameTo);
					data=tarFile;
				}
				String loadSql = sql.toString().replace("?", data.getAbsolutePath().replace("\\", "/"));
				Statement ps =null;
				try {
					ps = connection.createStatement();
					logger.info("正在入库....");
					logger.info("正在执行sql:"+loadSql);
					ps.execute(loadSql);
					logger.info("入库完成");
					data.delete();
				} catch (Exception e) {
					logger.error("",e);
				}finally{
					if(ps!=null)
						ps.close();
				}
			}
		} catch (Exception e) {
			logger.error("",e);
		}finally{
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		this.endToDo(error);
	}
	
	public abstract void endToDo(List<SpliderTaskVo> error);
}
