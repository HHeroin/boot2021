package com.hheroin.boot.boot2021.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DemoService {
    public String foo() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "service-foo";
    }
}

