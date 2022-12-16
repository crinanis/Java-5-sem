package com.example.Contacts.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AspectLogger.class);
    @Pointcut("execution(* com.example.Contacts.controller.MainController.*(..))")
    public void calledAtMainController(){}

    @After("calledAtMainController()")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* com.example.Contacts.controller.RegistrationController.*(..))")
    public void calledAtRegistrationController(){}

    @After("calledAtRegistrationController()")
    public void log2(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* com.example.Contacts.controller.UserController.*(..))")
    public void calledAtUserController(){}

    @After("calledAtUserController()")
    public void log3(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}