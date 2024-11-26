package com.umanath.twodatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(@Qualifier("readDataSource") DataSource readDataSource,
                                        @Qualifier("writeDataSource") DataSource writeDataSource) {
        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                // Use your logic to decide which data source to use based on the transaction context
                /*if (!org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive()) {
                    System.out.println("No active transaction");
                    return "write"; // Default to write if no transaction is active
                }

                boolean isReadOnly = org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly();
                String currentDb = isReadOnly ? "read" : "write";
                System.out.println("Transaction is read-only: " + isReadOnly);
                System.out.println("Current DataSource in determineCurrentLookupKey: " + currentDb);*/
                //return currentDb;
                System.out.println("determineCurrentLookupKey is invoked------------");
                System.out.println("Current DataSource in determineCurrentLookupKey: " +
                        DataSourceContextHolder.getDataSourceType());
                return DataSourceContextHolder.getDataSourceType();

            }
        };

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("read", readDataSource);
        dataSourceMap.put("write", writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(readDataSource);

        return routingDataSource;
    }
}
