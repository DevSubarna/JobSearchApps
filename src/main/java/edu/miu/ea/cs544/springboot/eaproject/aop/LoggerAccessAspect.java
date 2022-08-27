package edu.miu.ea.cs544.springboot.eaproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAccessAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("execution(* edu.miu.ea.cs544.springboot.eaproject.controller.*.delete*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before Deletion execution: " + joinPoint.getSignature().getDeclaringTypeName()
                + " : " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        log.info("After Deletion execution: " + joinPoint.getSignature().getDeclaringTypeName()
                + " : " + joinPoint.getSignature().getName());
    }

}
