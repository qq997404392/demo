package com.spring.test2;

import org.aspectj.lang.annotation.*;

@Aspect
public class Logging {

    /*@Pointcut("execution(* com.spring2.*.*(..))")
    private void selectAll() {}*/

    @Before("execution(* com.spring.test2.*.*(..))")
    public void beforeAdvice(){
        System.out.println("1. Going to setup student profile.");
    }
    @After("execution(* com.spring.test2.*.*(..))")
    public void afterAdvice(){
        System.out.println("2. Student profile has been setup.");
    }
    @AfterReturning(pointcut = "execution(* com.spring.test2.*.*(..))", returning = "retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println("3. Returning: " + retVal.toString() );
    }
    @AfterThrowing(pointcut = "execution(* com.spring.test2.*.*(..))", throwing = "ex")
    public void afterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("4. There has been an exception: " + ex.toString());
    }
}
