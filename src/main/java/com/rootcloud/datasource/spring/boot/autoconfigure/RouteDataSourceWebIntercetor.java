package com.rootcloud.datasource.spring.boot.autoconfigure;


import com.rootcloud.datasource.bean.RouteDataSourceHolder;
import com.rootcloud.datasource.spring.boot.autoconfigure.constants.RouteDataSourceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RouteDataSourceWebIntercetor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String dataSourceId = request.getHeader(RouteDataSourceConstants.X_DATASOURCE_ID);
        if (!StringUtils.isEmpty(dataSourceId)) {
            RouteDataSourceHolder.switchDatasource(dataSourceId);
            log.info(" [RouteDataSource] 设置数据源[{}]", dataSourceId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RouteDataSourceHolder.removeSwitchDatasource();
        log.info(" [RouteDataSource] 移除数据源");
    }

}
