package com.rootcloud.datasource.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class RouteDataSource extends AbstractRoutingDataSource {

    private String defaultDatasourceName;

    private final Set<String> datasourceNames = new TreeSet<>();

    private RouteDataSource() {
    }

    public static RouteDataSource initRouteDataSource(Map<String, ? extends DataSource> targetDataSources, String defaultDatasourceName, DataSource defaultTargetDataSource) {
        RouteDataSource routeDataSource = new RouteDataSource();
        // 转换 targetDataSources 的泛型为 AbstractRoutingDataSource 所需的泛型（为了尽量不修改 AbstractRoutingDataSource 的源码）
        Map<Object, Object> dataSources = new HashMap<>(targetDataSources.size());
        targetDataSources.forEach((dataSourceName, dataSource) -> {
            routeDataSource.getDatasourceNames().add(dataSourceName);
            dataSources.put(dataSourceName, dataSource);
        });
        routeDataSource.setTargetDataSources(dataSources);
        routeDataSource.setDefaultTargetDataSource(defaultTargetDataSource);
        routeDataSource.setDefaultDatasourceName(defaultDatasourceName);
        return routeDataSource;
    }

    public static void switchDatasource(String datasource) {
        RouteDataSourceHolder.switchDatasource(datasource);
    }

    public static String getSwitchDatasource() {
        return RouteDataSourceHolder.getSwitchDatasource();
    }

    public static void removeSwitchDatasource() {
        RouteDataSourceHolder.removeSwitchDatasource();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return RouteDataSourceHolder.getSwitchDatasource();
    }
}
