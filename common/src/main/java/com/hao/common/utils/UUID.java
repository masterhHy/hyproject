package com.hao.common.utils;

import org.apache.commons.lang.StringUtils;

public class UUID {

    public static String uuid32 () {
    	return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static String dealPhone(String phone){
        if(StringUtils.isNotBlank(phone)&&phone.length()==11){
            return phone.substring(0,3)+"****"+phone.substring(phone.length()-4,phone.length());
        }else {
            return phone;
        }
    }

}
