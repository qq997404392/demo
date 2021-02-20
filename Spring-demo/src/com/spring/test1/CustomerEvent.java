package com.spring.test1;

import org.springframework.context.ApplicationEvent;

public class CustomerEvent extends ApplicationEvent {
    public CustomerEvent(Object source) {
        super(source);
    }
    public String toString() {
        return "my customer event";
    }
}
