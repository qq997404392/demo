package com.spring.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Profile {
    @Autowired
    @Qualifier("student2")
    private Student student;
    public Profile(){
        System.out.println("Inside Profile constructor." );
    }
    public void print() {
        System.out.println("Age:" + student.getAge() + ",Name:" + student.getName());
    }
}
