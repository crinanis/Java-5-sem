package com.example.Contacts.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogUserAspect {
    @Pointcut("execution(public * com.example.Contacts.service.UserService.*(..))")
    public void callAtUserService(){}

    @Before("callAtUserService()")
    public void beforeCallMethod(JoinPoint jp) {
        log.info("before " + jp.toString());
    }

    @After("callAtUserService()")
    public void afterCallAt(JoinPoint jp) {
        log.info("after " + jp.toString());
    }
}
