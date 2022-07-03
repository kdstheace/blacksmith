package com.daniel.blacksmith.controller.fo;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/home", "", "/"})
    public String displayHome(){
        return "index";
    }
}
