package com.hao.authcenter.web;

import com.hao.authcenter.remote.UserServiceClient;
import com.hao.common.constant.WxConstant;
import com.hao.common.controller.BaseSpringController;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class MvcController extends BaseSpringController {
    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 登出回调
     * @param request
     * @param response
     */
    @RequestMapping("/backReferer")
    public void sendBack(HttpServletRequest request, HttpServletResponse response) {

        try {
            //sending back to client app
            String referer = request.getHeader("referer");
            if (referer != null) {
                int index = referer.indexOf("?");
                if(index != -1)
                    referer = referer.substring(0, index);
                response.sendRedirect(referer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 授权页面
     * @param model
     * @return
     */
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView authorizePage(Map<String, Object> model) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        return new ModelAndView("authorize", model);
    }
    @GetMapping(value="/login")
    public ModelAndView login(ModelAndView model) {

        model.setViewName("web_login");
        model.addObject("isMobile",this.isMobile());
        model.addObject("wxAppId",WxConstant.APP_ID);
        model.addObject("redirectUrl",WxConstant.REDIRECT_URL);
        model.addObject("isWX",this.isWxClient());
    	return model;
    }

    @RequestMapping(value="/open/checkUsername")
    @ResponseBody
    public ResponseData<Map<String,Object>> checkUsername(String username) {
        ResponseData<Map<String,Object>> res = new ResponseData<>();
        if(StringUtils.isNotBlank(username)){
            SysUser user =userServiceClient.getUserByUsername(username).getData();
            if(user==null){
                res.setStatus(true);
            }else{
                res.setStatus(false);
                res.setMessage("该用户已存在");
            }
        }else {
            res.setStatus(false);
            res.setMessage("用户名不能为空");
        }


        return res;
    }
    @RequestMapping(value="/open/register")
    @ResponseBody
    public ResponseData<Map<String,Object>> register( String username, String password, String code,String moduel) {
        ResponseData<Map<String,Object>> res = new ResponseData<>();
        if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
            ResponseData checkCode = this.checkCode(username,code,moduel);
            if(checkCode.getStatus()){
                SysUser user  = new SysUser();
                user.setUsername(username);
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder(6);
                user.setPassword(bc.encode(password));
                userServiceClient.register(user);
                res.setMessage("注册成功");
            }else{
                res.setMessage(checkCode.getMessage());

            }
            res.setStatus( checkCode.getStatus());

        }else {
            res.setStatus(false);
            res.setMessage("用户名或密码不能为空");
        }


        return res;
    }

    /**
     * 获取手机验证码
     * @param username
     * @param moduel
     * @return
     */
    @RequestMapping(value="/open/getCode")
    @ResponseBody
    public ResponseData<Map<String,Object>> getCode( String username, String moduel) {
        ResponseData<Map<String,Object>> res = new ResponseData<>();
        if(StringUtils.isNotBlank(username)){
            String randomCode =(int)((Math.random()*9+1)*100000)+"";
            //发送验证码

            //.....
            randomCode="123456";
            this.saveCode(username,randomCode,moduel);
            res.setStatus(true);
        }else {
            res.setStatus(false);
            res.setMessage("用户名不能为空");
        }


        return res;
    }

    /**
     * 微信二维码登录
     * @return
     */
    @RequestMapping(value="/open/wxqrcodeLogin")
    @ResponseBody
    public ResponseData<Map<String,Object>> wxqrcodeLogin(String code ) {
        ResponseData<Map<String,Object>> res = new ResponseData<>();


        return res;
    }




    /**
     * 主页
     * @param model
     * @return
     */
    @RequestMapping("/")
    public ModelAndView indexPage(Map<String, Object> model) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        // 获取全部客户端应用
        return new ModelAndView("index", model);
    }
}
