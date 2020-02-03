package com.hx;

import com.hx.context.HxContext;
import com.hx.service.Service_2;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        URL value=Thread.currentThread().getContextClassLoader().getResource("com");
        System.out.println(value.getProtocol());
        HxContext.initContext("com.hx");
        Service_2 service_2=(Service_2) HxContext.getBean("service_2");
        System.out.println(service_2.hello2());
    }
}
