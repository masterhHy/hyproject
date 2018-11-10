package com.hao.authcenter.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@SessionAttributes({"authorizationRequest"})
public class MvcController {


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
    	model.setViewName("login");
    	return model;
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
