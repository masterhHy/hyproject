package com.hao.time.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.common.entity.coin.TimeDream;
import com.hao.common.query.coin.TimeDreamQuery;
import com.hao.common.utils.UUID;
import com.hao.time.dao.TimeDreamMapper;
import com.hao.time.service.TimeDreamService;

import tk.mybatis.mapper.entity.Example;

@Service
public class TimeDreamServiceImpl extends BaseServiceImpl<TimeDream> implements TimeDreamService {
	@Autowired
	private TimeDreamMapper timeDreamMapper;
	@Override
	public List<TimeDream> getDreamData(TimeDreamQuery query) {
		Example record = new Example(TimeDream.class);
		record.createCriteria().andEqualTo("userId", query.getUserId()).andEqualTo("isDelete", 0);
		if(StringUtils.isNotBlank(query.getId())){
			record.and().andEqualTo("id", query.getId());
		}
		if(StringUtils.isNotBlank(query.getOrderColumn())){
			if(query.getSort().toLowerCase().equals("desc")){
				record.orderBy(query.getOrderColumn()).desc();
			}else{
				record.orderBy(query.getOrderColumn()).asc();
			}
		}
		return timeDreamMapper.selectByExample(record);
	}
	@Override
	public void addOrUpdate(TimeDream dream) {
		TimeDream save =null;
		if(StringUtils.isNotBlank(dream.getId())){
			save =timeDreamMapper.selectByPrimaryKey(dream.getId());
			save.setPrice(dream.getPrice());
			save.setPriority(dream.getPriority());
			save.setUpdateTime(new Date());
			save.setDescription(dream.getDescription());
			save.setTitle(dream.getTitle());
			timeDreamMapper.updateByPrimaryKeySelective(save);
		}else{
			save=dream;
			save.setId(UUID.uuid32());
			save.setCreateTime(new Date());
			save.setIsDelete(0);
			timeDreamMapper.insertSelective(save);
		}
		
	}
    

}
