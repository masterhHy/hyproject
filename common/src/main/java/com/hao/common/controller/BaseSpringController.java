package com.hao.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
import com.hao.common.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Date;

public class BaseSpringController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public String getUserId(){
    	if(this.getUser()==null){
    		return null;
    	}
        return this.getUser().getId();
    }
    public SysUser getUser(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String userinfo = request.getHeader("user_info");
        try {
            userinfo = URLDecoder.decode(userinfo,"UTF-8");
        }catch (Exception e){
            logger.error("",e);
        }
        SysUser user = JSONObject.parseObject(userinfo, SysUser.class);
        return user;
    }
    //判断是否为手机浏览器
    public boolean isMobile() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        boolean isMoblie = false;
        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        if (request.getHeader("User-Agent") != null) {
            String agent=request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

    /**
     *  按不同模块的验证码来保存验证信息，方便后台检验验证码
     */
    public void saveCode(String phone,String code,String module){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //1分钟过期
        Date expireTime = DateHelper.add(new Date(), 5, 1);
        session.setAttribute(phone+code+module,expireTime);
    }

    public ResponseData checkCode(String phone, String code, String module){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Date time = (Date) session.getAttribute(phone+code+module);
        ResponseData res = new ResponseData();
        if(time==null){
            res.setStatus(false);
            res.setMessage("验证码错误");
        }else if(System.currentTimeMillis()>time.getTime()){
            res.setStatus(false);
            res.setMessage("验证码已过期");
        }else{
            res.setStatus(true);
            res.setMessage("验证码正确");
            session.removeAttribute(phone+code+module);
        }
        return res ;
    }

    public boolean isWxClient(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String ua =  request.getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") != -1) {// 是微信浏览器
           return true;
        }else{
            return false;
        }
    }



}
