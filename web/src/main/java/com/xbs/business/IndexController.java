package com.xbs.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Value("${spring.front}")
    private String front;

    @RequestMapping("/web/**")
    public String index(ModelMap map) {
        map.addAttribute("front", front);
        return "index";
    }
}
