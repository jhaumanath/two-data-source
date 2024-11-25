package com.umanath.twodatasource.config;

public class RoutingContext {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setCurrentDb(String dbType) {
        CONTEXT.set(dbType);
    }

    public static String getCurrentDb() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}

