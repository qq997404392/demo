package com.spring.test1;

import org.springframework.context.ApplicationListener;

public class CustomerEventHandler implements ApplicationListener<CustomerEvent> {
    @Override
    public void onApplicationEvent(CustomerEvent customerEvent) {
        System.out.println(customerEvent.toString());
    }
}
