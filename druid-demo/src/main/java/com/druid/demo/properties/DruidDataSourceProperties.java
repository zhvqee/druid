package com.druid.demo.properties;

import com.alibaba.druid.filter.Filter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo.properties
 * @ClassName: DruidDataSourceProperties
 * @Description:
 * @Date: 2021/11/8 10:21 下午
 * @Version: 1.0
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = DruidDataSourceProperties.prefix)
public class DruidDataSourceProperties {

    public static final String prefix = "druid.datasource";
    private static final int CONNECTION_INITTIAL_SIZE = 5;

    private static final int CONNECTION_MAX_SIZE = 20;

    private static final int CONNNECTION_MIN_SIZE = 5;

    private static final int CONNECTION_MAX_WAIT_TIME = 3000;

    private static final int CONNECTION_POOLED_PREPARED_STATEMENT_SIZE = 50;

    private static final String DEFAULT_SELECT_QUERY = "select 1";

    private static final int DEFAULT_SELECT_QUERY_TIME_OUT = 500;

    private static final int DEFAULT_EVICTION_MILLIS = 1500;
    private static final int DEFAULT_MIN_EVICTION_IDLE_TIME_MOLLIS = 30000;
    private static final int DEFAULT_MAX_EVICTION_IDLE_TIME_MOLLIS = 30000;
    private static final int DEFAULT_PHY_TIMEOUT_MILLS = 12 * 60 * 1000;
    private static final int DEFAULT_REMOVE_ABANDONED_TIME_OUT = 30;
    private static final Boolean DEFAULT_AUTO_COMMIT = Boolean.TRUE;

    private static final String DEFAULT_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


    private String driverClassName = DEFAULT_DRIVER_CLASS_NAME;

    /**
     * jdbc url
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 初始的连接数量
     */
    private Integer initialSize = CONNECTION_INITTIAL_SIZE;

    /**
     * 最大连接池数量
     */
    private Integer maxActive = CONNECTION_MAX_SIZE;


    /**
     * 空闲连接数量
     */
    private Integer minIdle = CONNNECTION_MIN_SIZE;

    /**
     * 连接最大等待时间，单位毫秒。
     */
    private Integer maxWait = CONNECTION_MAX_WAIT_TIME;

    /**
     * 池化的 PreparedStatements
     */
    private Boolean poolPreparedStatements = false;

    /**
     * 池化时，最大连接数
     */
    private Integer maxPoolPreparedStatementPerConnectionSize = CONNECTION_POOLED_PREPARED_STATEMENT_SIZE;

    /**
     * 检测连接是否有效
     */
    private String validationQuery = DEFAULT_SELECT_QUERY;

    /**
     * 检测 validationQuery 查询的超时时间
     */
    private Integer validationQueryTimeout = DEFAULT_SELECT_QUERY_TIME_OUT;

    /**
     * 如果为true（默认false），当应用向连接池申请连接时，连接池会判断这条连接是否是可用的。
     */
    private Boolean testOnBorrow = false;

    /**
     * 如果为true（默认false），当应用使用完连接，连接池回收连接的时候会判断该连接是否还可用。
     */
    private Boolean testOnReturn = false;

    /**
     * 如果为true（默认true），当应用向连接池申请连接，并且testOnBorrow为false时，连接池将会判断连接是否处于空闲状态，如果是，则验证这条连接是否可用。
     */
    private Boolean testWhileIdle = true;

    /**
     * timeBetweenEvictionRunsMillis可以用来控制空闲连接数的回收周期
     * timeBetweenEvictionRunsMillis可以用来控制回收泄露连接的周期
     * timeBetweenEvictionRunsMillis连接的空闲时间大于该值testWhileIdle才起作用
     */
    private Integer timeBetweenEvictionRunsMillis = DEFAULT_EVICTION_MILLIS;

    /**
     * 空闲移除时间MIN
     */
    private Integer minEvictableIdleTimeMillis = DEFAULT_MIN_EVICTION_IDLE_TIME_MOLLIS;

    /**
     * 空闲移除时间MAX
     */
    private Integer maxEvictableIdleTimeMillis = DEFAULT_MAX_EVICTION_IDLE_TIME_MOLLIS;

    /**
     * 一条物理连接的最大存活时间(单位: ms)，默认120分钟
     */
    private Integer phyTimeoutMillis = DEFAULT_PHY_TIMEOUT_MILLS;

    /**
     * 是否移除异常连接
     */
    private Boolean removeAbandoned = true;

    /**
     * 异常异常的连接时间
     */
    private Integer removeAbandonedTimeout = DEFAULT_REMOVE_ABANDONED_TIME_OUT;

    /**
     * 连接参数？？？
     */
    private String connectionProperties;

    /**
     * 是否自动提交
     */
    private Boolean defaultAutoCommit = DEFAULT_AUTO_COMMIT;

    /**
     * 记录被判定为异常的连接, 默认值：true
     */
    private Boolean logAbandoned = true;


    /**
     * 过滤链
     */
    private List<Filter> proxyFilters = new ArrayList<>();


    /**
     * 使用非公平锁，建议开启，能大幅提升性能，影响就是不保证公平性
     */
    private Boolean useUnfairLock = true;


}