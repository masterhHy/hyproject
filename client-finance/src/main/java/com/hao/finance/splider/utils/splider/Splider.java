package com.hao.finance.splider.utils.splider;

import com.hao.finance.splider.utils.splider.handler.ObjectHandler;
import com.hao.finance.splider.utils.splider.thread.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Splider {
	Logger logger =  LoggerFactory.getLogger(this.getClass());
	private ObjectHandler handler;
	public String unicode="UTF-8";
	private HttpConnectionManager pools;

	
	public void setPools(HttpConnectionManager pools) {
		this.pools = pools;
	}

	public Splider(ObjectHandler handler) {
		this.handler=handler;
	}
	
	
	public void readUrl(String url,String type,Map<String,Object> params){
		CloseableHttpClient client =null;
		if(pools!=null){
			client = pools.getHttpClient();
		}else{
			client = HttpClients.createDefault();
		}
		CloseableHttpResponse response=null;
		if(params==null){
			params = new HashMap<>();
		}
		
		
		try {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for(String key: params.keySet()){
				pairs.add(new BasicNameValuePair(key,params.get(key)==null?"":params.get(key).toString()));
			}
			
			logger.info("正在爬去连接----->"+url);
			if("get".equalsIgnoreCase(type)){
				//建立url
				URIBuilder builder = new URIBuilder(url);
				builder.setParameters(pairs);
				HttpGet get =null;
				if(pools!=null){
					get = pools.getDefaultHttpGet();
				}else{
					get = new HttpGet();
				}
				get.setURI(builder.build());	
				
				//访问读取页面
				response = client.execute(get);
				HttpEntity entity = response.getEntity();
				String data = EntityUtils.toString(entity,unicode);
				handler.webOriginalDataStr(data);
			}else if("post".equalsIgnoreCase(type)){
				HttpPost post =null;
				if(pools!=null){
					post = pools.getDefaultHttpPost();
				}else{
					post = new HttpPost();
				}
				post.setURI(new URI(url));
				
				post.setEntity(new UrlEncodedFormEntity(pairs,unicode));
				
				//获取信息
				response = client.execute(post);
				HttpEntity entity = response.getEntity();
				String data = EntityUtils.toString(entity,unicode);
				handler.webOriginalDataStr(data);
			}else{
				throw new RuntimeException("请求类型出错 type 应该为post/get");
			}
			
			logger.info("完成爬去连接----->"+url);
		} catch (Exception e) {
			logger.error("爬去数据出错了",e);
			throw new RuntimeException();
		}finally {
			this.close(client, response);
		}
		
		
	}
	
	
	private void close(CloseableHttpClient client,CloseableHttpResponse response){
		if(response!=null){
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(client!=null&&pools==null){
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
