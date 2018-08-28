package com.hao.splidercenter.utils.splider.thread;

import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpConnectionManager {

    private PoolingHttpClientConnectionManager cm = null;
    private HttpHost proxy = null;
    private CredentialsProvider credsProvider = null;
    @Value("${httpconfig.useProxy}")
    private Boolean useProxy;
    
    
    public Boolean getUseProxy() {
		return useProxy;
	}

	@Value("${httpconfig.proxyUser}")
    private String proxyUser;
    @Value("${httpconfig.proxyPass}")
    private String proxyPass;
    @Value("${httpconfig.proxyHost}")
    private String proxyHost;
    @Value("${httpconfig.proxyPort}")
    private int proxyPort;
    
    
    @PostConstruct
    public void init() {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        //创建连接池
        cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        if(useProxy){
        	cm.setMaxTotal(20);
        	cm.setDefaultMaxPerRoute(5);
        }else{
        	cm.setMaxTotal(5);
        	cm.setDefaultMaxPerRoute(1);
        }
        
        
        //创建访问凭证通过
        credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUser, proxyPass));
        //代理这地址
        proxy = new HttpHost(proxyHost, proxyPort, "http");
        
        
    }

    public CloseableHttpClient getHttpClient() {       
        HttpClientBuilder mb = HttpClients.custom().setConnectionManager(cm);
                
        if(useProxy){
        	mb.setDefaultCredentialsProvider(credsProvider);
        }
        
        return mb.build();
    }
    
    public HttpGet getDefaultHttpGet(){
    	HttpGet get = new HttpGet();
    	Builder b = RequestConfig.custom()
								 .setConnectionRequestTimeout(5*60000)
								 .setConnectTimeout(5*60000)
								 .setSocketTimeout(5*60000)
								 .setExpectContinueEnabled(false);
									   			 
    	if(useProxy){
    		b.setProxy(proxy);
    	}
    	
    	get.setConfig(b.build());
    	get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
    	return get;
    }
    public HttpPost getDefaultHttpPost(){
    	HttpPost post = new HttpPost();
    	Builder b = RequestConfig.custom()
				 .setConnectionRequestTimeout(5*60000)
				 .setConnectTimeout(5*60000)
				 .setSocketTimeout(5*60000)
				 .setExpectContinueEnabled(false);
					   			 
		if(useProxy){
			b.setProxy(proxy);
		}

		post.setConfig(b.build());
    	post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
    	return post;
    }
    
    
    
}
