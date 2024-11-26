package com.umanath.twodatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TwoDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoDataSourceApplication.class, args);
	}
}
