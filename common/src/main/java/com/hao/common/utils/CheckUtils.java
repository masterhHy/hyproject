package com.hao.common.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class CheckUtils {

	public static Boolean checkPhone(String phone){
		if(StringUtils.isNotBlank(phone)){
			String pattern = "^1[34578]\\d{9}$";
			return Pattern.matches(pattern, phone);
		}else{
			return false;
		}
	}
	
}
