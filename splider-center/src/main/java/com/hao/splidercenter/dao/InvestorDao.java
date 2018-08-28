package com.hao.splidercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hao.splidercenter.po.Investor;

public interface InvestorDao extends JpaRepository<Investor,String> {
	public List<Investor> findByName(String name);
}
