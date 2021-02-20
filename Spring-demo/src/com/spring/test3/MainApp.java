package com.spring.test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class MainApp {
    public static void main(String args[]) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        StudentImpl impl = (StudentImpl) context.getBean("studentImpl");
        impl.create("张三", 18, 99, 2010);
        impl.create("李四", 19, 97, 2010);
        impl.create("王五", 20, 100, 2011);

        List<StudentMarks> list =  impl.listStudents();
        for (StudentMarks record : list) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }


       /* Map<String, Object> RSAKeys = RSAUtil.initKey();
        System.out.println("私钥:\n"+RSAKeys.get("RSAPrivateKey"));
        System.out.println("公钥:\n"+RSAKeys.get("RSAPublicKey"));*/

    }

}
