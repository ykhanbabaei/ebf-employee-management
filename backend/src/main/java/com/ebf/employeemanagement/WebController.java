package com.ebf.employeemanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping({"/companies/**"})
    public String index() {
        return "forward:/index.html";
    }
}