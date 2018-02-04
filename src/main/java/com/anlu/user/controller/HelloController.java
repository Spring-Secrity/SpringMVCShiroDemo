package com.anlu.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    private static final transient Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        log.info("访问了HelloController");
        return mv;
    }

}
