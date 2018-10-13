package com.hao.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FinanceClientApplication {
   public static void main(String[] args){
	   SpringApplication.run(FinanceClientApplication.class, args);
   }
   
  /* @Component
   class Start implements CommandLineRunner {
	   	@Autowired
	   	private InvestorService investorService;
	   	
		@Override
		public void run(String... args) throws Exception {
			List<SpliderInvestor> selectAll = investorService.selectAll();
			System.out.println(selectAll);
		}
	   
   }*/
}
