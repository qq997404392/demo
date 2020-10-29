package com.springcloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//@EnableHystrix
//@EnableCircuitBreaker
//@EnableHystrixDashboard
public class HystrixApplication {

	public static void main(String[] args) {
		System.out.println("启动开始");
		SpringApplication.run(HystrixApplication.class, args);
		System.out.println("启动完成");
	}

}
