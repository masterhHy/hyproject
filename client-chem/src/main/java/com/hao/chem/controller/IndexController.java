package com.hao.chem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/")
    public ModelAndView idnex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/appearance/index");
        return modelAndView;
    }
}
