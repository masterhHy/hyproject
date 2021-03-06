package com.hao.time.controller;

import com.hao.common.controller.BaseSpringController;
import com.hao.common.entity.coin.TimeDream;
import com.hao.common.pojo.ResponseData;
import com.hao.common.query.coin.TimeDreamQuery;
import com.hao.time.service.TimeDreamService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TimeDreamController extends BaseSpringController {
	@Autowired
    private TimeDreamService timeDreamService;
	
	@RequestMapping("/dream/getDreamData")
	public ResponseData<List<TimeDream>> getDreamData(TimeDreamQuery query){
		ResponseData<List<TimeDream>> res = new ResponseData<>();
		query.setUserId(this.getUserId());
		res.setData(timeDreamService.getDreamData(query));
		res.setCode(ResponseData.SUCCESS_CODE);
		return res;
	}
	@RequestMapping("/dream/getDreamDataById")
	public ResponseData<TimeDream> getDreamDataById(TimeDreamQuery query){
		ResponseData<TimeDream> res = new ResponseData<>();
		if(StringUtils.isNotBlank(query.getId())){
			query.setUserId(this.getUserId());
			res.setData(timeDreamService.getDreamData(query).get(0));
		}
		res.setCode(ResponseData.SUCCESS_CODE);
		return res;
	}
	
	
	
	@RequestMapping("/dream/addOrUpdate")
	public ResponseData<List<TimeDream>> addOrUpdate(TimeDream dream){
		ResponseData<List<TimeDream>> res = new ResponseData<>();
		dream.setUserId(this.getUserId());
		timeDreamService.addOrUpdate(dream);
		res.setCode(ResponseData.SUCCESS_CODE);
		return res;
		
	}
	@RequestMapping("/dream/deleteById")
	public ResponseData<List<TimeDream>> deleteById(TimeDream dream){
		ResponseData<List<TimeDream>> res = new ResponseData<>();
		if(StringUtils.isNotBlank(dream.getId())){
			dream = timeDreamService.selectByPrimaryKey(dream);
			dream.setIsDelete(1);
			timeDreamService.updateByPrimaryKey(dream);
		}
		res.setCode(ResponseData.SUCCESS_CODE);
		return res;
		
	}
}
