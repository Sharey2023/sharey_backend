package com.sharey.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sharey")
@ApiIgnore()

public class TestController {
    //private final PostService postService;
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
}
