package com.epam.user_interface;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("PMT")
public class WebController
{
//    @RequestMapping("/")
//    public String masterHomeMenu()
//    {
//        return "MasterHomeMenu";
//    }
    @RequestMapping("/")
    public String index() {
        return "masterHomeMenu";
    }
}
