package com.sharey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    // 최초 접속 시 index페이지 로드
    @GetMapping("/")
    public String index(){
        return "index";
    }
}