package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public void home() {
        System.out.println("home");
    }
    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "about";
    }
}
