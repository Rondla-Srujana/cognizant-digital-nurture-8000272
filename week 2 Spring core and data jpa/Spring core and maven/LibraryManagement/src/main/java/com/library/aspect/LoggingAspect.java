package com.library.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.library.service.BookService.*(..))")
    public void logBefore() {
        System.out.println("[AOP LOG - BEFORE] Method execution starting...");
    }
    @After("execution(* com.library.service.BookService.*(..))")
    public void logAfter() {
        System.out.println("[AOP LOG - AFTER] Method execution finished.");
    }
    @Around("execution(* com.library.service.BookService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("[AOP LOG - TIME] " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + " ms");
        return result;
    }
}
