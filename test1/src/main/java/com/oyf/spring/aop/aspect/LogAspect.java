package com.oyf.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author ouyangfei
 * @date Created in 2021/2/22
 * @description AOP切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    // GetMapping注解修饰的方法
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void pointcut1() {}

    // controller1包下所有类的所有方法
    @Pointcut("execution( * com.oyf.spring.aop.controller1.*.*(..))")
    private void pointcut2() {}

    // controller2包下所有类的所有方法
    @Pointcut("execution( * com.oyf.spring.aop.controller2.*.*(..))")
    private void pointcut3() {}

    @Before("pointcut1()")
    public void before1(JoinPoint joinPoint){
        log.info("pointcut1() >> Before >> {}", joinPoint.toString());
    }

    @Before("pointcut2()")
    public void before2(JoinPoint joinPoint){
        log.info("pointcut2() >> Before >> {}", joinPoint.toString());
        log.info("目标方法: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // 参数名数组
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 参数值数组
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("参数名: {}, 参数值: {}", argNames[i], args[i]);
        }
    }

    @Before("pointcut2() || pointcut3()")
    public void before3(JoinPoint joinPoint){
        log.info("pointcut3() >> Before >> {}", joinPoint.toString());
    }

    @After("pointcut1()")
    public void after1(JoinPoint joinPoint){
        log.info("pointcut1() >> After >> {}", joinPoint.toString());
        log.info("参数值: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("pointcut2()")
    public void after2(JoinPoint joinPoint){
        log.info("pointcut2() >> After >> {}", joinPoint.toString());
        log.info("参数值: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("pointcut3()")
    public void after3(JoinPoint joinPoint){
        log.info("pointcut3() >> After >> {}", joinPoint.toString());
    }

    @Around("pointcut2()")
    public void around1(ProceedingJoinPoint joinPoint) {
        log.info("pointcut2() >> Around >> {}", joinPoint.toString());
        log.info("目标方法: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // 参数名数组
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 参数值数组
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("参数名: {}, 参数值: {}", argNames[i], args[i]);
        }
        args[0] = "我爱中国2";

        // 动态修改参数
        try {
            joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
