package com.test.dubbo;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableCaching
@SpringBootApplication
@EnableDubboConfiguration
public class App 
{
    private static CountDownLatch cdl = new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
	    SpringApplication.run(App.class, args).registerShutdownHook();
	    cdl.await();
    }
}
