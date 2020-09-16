package com.demo.rsokcet.bff.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphQlPageController {
    @GetMapping("/")
    public String forward() {
        return "forward:/graphiql/index.html";
    }
}
