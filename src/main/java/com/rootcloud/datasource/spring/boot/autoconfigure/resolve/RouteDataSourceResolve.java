package com.rootcloud.datasource.spring.boot.autoconfigure.resolve;

import com.rootcloud.datasource.spring.boot.autoconfigure.constants.RouteDataSourceTypeEnum;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 路由数据源解析接口
 * <p>
 * 实现该接口并且注册为 bean 就可以在启动的时候扫描注册到对应的数据源
 */
public interface RouteDataSourceResolve {

    /**
     * 数据源类型
     */
    RouteDataSourceTypeEnum type();

    /**
     * 解析数据源
     *
     * @return key 为数据源名称,value 为数据源
     */
    Map<String, ? extends DataSource> resolve();
}
