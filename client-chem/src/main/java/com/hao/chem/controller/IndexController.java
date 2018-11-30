package com.hao.chem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/test2")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/test");
        modelAndView.addObject("user","user");
        return modelAndView;
    }
}
