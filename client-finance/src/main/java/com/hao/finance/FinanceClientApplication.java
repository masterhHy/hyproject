package com.hao.finance;

import com.hao.finance.splider.scheduled.task.FinanceSpliderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class FinanceClientApplication implements CommandLineRunner {
   public static void main(String[] args){
	   SpringApplication.run(FinanceClientApplication.class, args);
   }

    @Autowired
    private FinanceSpliderTask financeSpliderTask;
    @Override
    public void run(String... args) throws Exception {
        financeSpliderTask.run();
    }

}
