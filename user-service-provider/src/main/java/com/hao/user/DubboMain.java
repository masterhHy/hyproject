package com.hao.user;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:dubbo/dubbo-config.properties")  
@ImportResource({ "classpath:dubbo/*.xml" }) 
public class DubboMain {

	@Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(DubboMain.class).web(WebApplicationType.NONE)
                .run(args);
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }
}
