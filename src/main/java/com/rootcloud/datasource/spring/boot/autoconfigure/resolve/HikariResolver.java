package com.rootcloud.datasource.spring.boot.autoconfigure.resolve;

import com.rootcloud.datasource.spring.boot.autoconfigure.constants.RouteDataSourceTypeEnum;
import com.rootcloud.datasource.spring.boot.autoconfigure.properties.RouteDataSourceHikariProperties;
import com.rootcloud.datasource.spring.boot.autoconfigure.properties.RouteDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Hikari 路由数据源实现
 */
@ConditionalOnClass(HikariDataSource.class)
@EnableConfigurationProperties(value = {RouteDataSourceHikariProperties.class})
public class HikariResolver implements RouteDataSourceResolve {

    static final RouteDataSourceTypeEnum ROUTE_DATASOURCE_TYPE = RouteDataSourceTypeEnum.HIKARI;

    RouteDataSourceProperties routeDataSourceProperties;

    RouteDataSourceHikariProperties routeDataSourceHikariProperties;

    public HikariResolver(RouteDataSourceProperties routeDataSourceProperties,
                          RouteDataSourceHikariProperties routeDataSourceHikariProperties) {
        this.routeDataSourceProperties = routeDataSourceProperties;
        this.routeDataSourceHikariProperties = routeDataSourceHikariProperties;
    }

    @Override
    public RouteDataSourceTypeEnum type() {
        return ROUTE_DATASOURCE_TYPE;
    }

    @Override
    public Map<String, ? extends DataSource> resolve() {
        return ResolveRouteDataSourceUtils.resolved(
                routeDataSourceProperties.getEnableDatasource(),
                type().getName(),
                routeDataSourceHikariProperties.getHikari());
    }
}
