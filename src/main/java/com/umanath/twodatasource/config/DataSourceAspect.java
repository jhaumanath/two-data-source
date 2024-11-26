package com.umanath.twodatasource.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void switchDataSource(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Transactional transactional = method.getAnnotation(Transactional.class);
        System.out.println(method.getName());

        if (transactional.readOnly()) {
            System.out.println("setting datasource type to read");
            DataSourceContextHolder.setDataSourceType("read");
        } else {
            System.out.println("setting datasource type to write");
            DataSourceContextHolder.setDataSourceType("write");
        }
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void clearDataSource(JoinPoint joinPoint) {
        DataSourceContextHolder.clearDataSourceType();
    }
}

