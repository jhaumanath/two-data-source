package com.umanath.twodatasource.config;

import com.umanath.twodatasource.annotation.ReadOnly;
import com.umanath.twodatasource.app.DataSourceType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class DataSourceReadOnlyAspect {
    @Around("@annotation(readOnly)")
    public Object proceed(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable {
        try {
            DataSourceContextHolder.setDataSourceType(DataSourceType.READER);
            return joinPoint.proceed();
        } finally {
            // Restore the default datasource after the call
            System.out.println("Resetting DataSource Type");
            DataSourceContextHolder.clearDataSourceType();
        }
    }
}
