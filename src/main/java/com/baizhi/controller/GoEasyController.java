package com.baizhi.controller;

import io.goeasy.GoEasy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goeasy")
public class GoEasyController {
    @RequestMapping("goeasy")
    public void goEasy(String aa) {
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-a29d976b67a941dbaa2783636a024159");
        goEasy.publish("aa",aa);
        System.out.println(aa);

    }
}
