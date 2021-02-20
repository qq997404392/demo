package com.spring.test1;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

// 监听ContextStartedEvent
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("ContextStartedEvent Received");
    }
}
