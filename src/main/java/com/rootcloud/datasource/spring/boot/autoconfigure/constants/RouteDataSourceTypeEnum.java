package com.rootcloud.datasource.spring.boot.autoconfigure.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 路由数据源类型
 */
@Getter
@AllArgsConstructor
public enum RouteDataSourceTypeEnum {

    /**
     * hikari 类型
     */
    HIKARI("hikari"),


    /**
     * TODO druid 待实现
     */
//    DRUID("druid"),

    ;

    private String name;
}
