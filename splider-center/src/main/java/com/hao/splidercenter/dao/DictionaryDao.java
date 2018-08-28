package com.hao.splidercenter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hao.splidercenter.po.Dictionary;

public interface DictionaryDao extends JpaRepository<Dictionary,String> {
	public List<Dictionary> findByNameAndSort(String name,String sort);
	
	public List<Dictionary> findBySortAndParentIdIsNull(String sort);
	
	@Query(nativeQuery=true,value="SELECT MAX(CAST(REPLACE(d.code,CONCAT(?1,'_'),'') AS SIGNED )) num FROM splider_dictionary d WHERE d.sort=?1 " )
	public Integer getMaxIndexBySort(String sort);
	
}
