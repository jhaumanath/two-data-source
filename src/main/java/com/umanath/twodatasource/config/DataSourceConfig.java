package com.umanath.twodatasource.config;

import com.umanath.twodatasource.app.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
            @Override
            protected Object determineCurrentLookupKey() {
                DataSourceType dataSourceType = DataSourceContextHolder.getDataSourceType();
                String dataSourceName = (dataSourceType == null) ? "Writer" : dataSourceType.toString();
                logger.info("Datasource selected: {}", dataSourceName);
                return dataSourceType == null ? DataSourceType.WRITER:dataSourceType;
            }
        };
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.READER, readDataSource);
        dataSourceMap.put(DataSourceType.WRITER, writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);

        return routingDataSource;
    }
}
