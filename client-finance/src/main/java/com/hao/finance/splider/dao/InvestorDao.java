package com.hao.finance.splider.dao;

import com.hao.finance.splider.po.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestorDao extends JpaRepository<Investor,String> {
	public List<Investor> findByName(String name);
}
