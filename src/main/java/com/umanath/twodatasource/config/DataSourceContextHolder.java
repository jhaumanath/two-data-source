package com.umanath.twodatasource.config;

import com.umanath.twodatasource.app.DataSourceType;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType dataSourceType) {
        System.out.println("Context Holder - setting datasource type to : " + dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType() {
        DataSourceType dataSourceType = contextHolder.get() == null ? DataSourceType.WRITER : contextHolder.get();
        System.out.println("Context Holder - getting datasource type as " + dataSourceType);
        return dataSourceType;
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
