package com.umanath.twodatasource.config;

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        System.out.println("setting datasource type to read " + dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        System.out.println("getting datasource type to read " + contextHolder.get());
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
