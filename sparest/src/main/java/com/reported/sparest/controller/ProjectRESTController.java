package com.reported.sparest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectRESTController {

    @PostMapping("/test")
    public String dosth(){
        return "test";
    }
}
