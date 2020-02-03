package com.hx.service;


import com.hx.annoation.HxAutowired;
import com.hx.annoation.HxService;

@HxService
public class Service_2 {
    @HxAutowired
    private Service_1 service_1;


    public String hello2(){
        return "hello2"+service_1.getClass();
    }
}
