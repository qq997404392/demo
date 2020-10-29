package com.spring2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjtlcb.fcloud.utils.SecurityUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.BASE64Encoder;

import java.util.Iterator;

public class MainApp {
    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Student student = (Student) context.getBean("student");
        student.getName();
        student.printThrowException();

    }




}
