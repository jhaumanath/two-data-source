package com.umanath.twodatasource.config;

import com.umanath.twodatasource.app.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSourceConfig extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceConfig.class);

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DataSourceContextHolder.getDataSourceType();
        String dataSourceName = (dataSourceType == null) ? "Writer" : dataSourceType.toString();
        logger.info("Datasource selected: {}", dataSourceName);
        return dataSourceType == null ? DataSourceType.WRITER:dataSourceType;
    }
}