package com.rootcloud.datasource.bean;

public class RouteDataSourceHolder {

    private RouteDataSourceHolder() {
    }

    private static final ThreadLocal<String> ROUTE_DATASOURCE_IN_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    public static void switchDatasource(String datasource) {
        RouteDataSourceHolder.ROUTE_DATASOURCE_IN_THREAD_LOCAL.set(datasource);
    }

    public static String getSwitchDatasource() {
        return RouteDataSourceHolder.ROUTE_DATASOURCE_IN_THREAD_LOCAL.get();
    }

    public static void removeSwitchDatasource() {
        RouteDataSourceHolder.ROUTE_DATASOURCE_IN_THREAD_LOCAL.remove();
    }
}
