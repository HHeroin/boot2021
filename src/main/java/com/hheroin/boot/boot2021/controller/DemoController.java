package com.hheroin.boot.boot2021.controller;

import com.hheroin.boot.boot2021.annotation.AccessTime;
import com.hheroin.boot.boot2021.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/foo")
    @AccessTime("second")
    public String foo(@RequestParam("bar") String bar) {
        String foo = demoService.foo();
        log.info("-----{}",foo);
        return foo;
    }
}
