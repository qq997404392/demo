package com.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import sun.misc.BASE64Encoder;

public class MainApp {
    public static void main(String args[]) {
        /*ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
        CustomerEventPublisher cvp = (CustomerEventPublisher) context.getBean("customerEventPublisher");
        cvp.publish();*/

       /* ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");*/




        /*TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();

        Profile profile = (Profile) context.getBean("profile");
        profile.print();*/


       /* AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(HelloWorldConfig.class);
        ctx.refresh();
        ctx.getBean(HelloWorld.class);*/

        String s = "{\"equipmentGroup\": {\"aplyCnt\": \"中国\",\"zmWsg\": \"157\"}}";
        System.out.println(s);

        String base64 = (new BASE64Encoder()).encodeBuffer(s.getBytes());
        base64 = base64.replaceAll("[\\s*\t\n\r]", "");
        System.out.println(base64);
        System.out.println("111");
    }
}
