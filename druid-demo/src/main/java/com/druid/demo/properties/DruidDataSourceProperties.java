package com.druid.demo.properties;

import com.alibaba.druid.filter.Filter;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

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
@ConfigurationProperties(prefix = DruidDataSourceProperties.prefix)
public class DruidDataSourceProperties implements ApplicationContextAware, InitializingBean {

    public static final String prefix = "druid.datasource";

    private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceProperties.class);

    private ApplicationContext applicationContext;

    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    private String driverClassName;

    /**
     * JDBC url of the database.
     */
    private String url;

    /**
     * Login user of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    /**
     * 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 (默认大小为3)
     */
    private Integer initialSize;

    /**
     * 最大连接池数量
     */
    private Integer maxActive;


    /**
     * 最小连接池数量
     */
    private Integer minIdle;

    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁
     */
    private Integer maxWait;

    /**
     * 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
     */
    private Boolean poolPreparedStatements;

    /**
     * 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
     */
    private Integer maxPoolPreparedStatementPerConnectionSize;

    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。默认为select
     * 1
     */
    private String validationQuery;

    /**
     * 单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法, 默认为1s
     */
    private Integer validationQueryTimeout;

    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private Boolean testOnBorrow;

    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private Boolean testOnReturn;

    /**
     * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。默认为true
     */
    private Boolean testWhileIdle;

    /**
     * 有两个含义：默认15s 单位：ms
     * 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
     * 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private Integer timeBetweenEvictionRunsMillis;

    /**
     * 连接保持空闲而不被驱逐的最长时间(空闲多久可以认为是空闲太长而需要剔除),默认54s
     */
    private Integer minEvictableIdleTimeMillis;

    /**
     * 如果空闲时间太长即使连接池所剩连接 < minIdle 也要被剔除,默认54s
     */
    private Integer maxEvictableIdleTimeMillis;

    /**
     * 一条物理连接的最大存活时间(单位: ms)，默认120分钟
     */
    private Integer phyTimeoutMillis;

    /**
     * 强行关闭从连接池获取而长时间未归还给druid的连接(认为异常连接),默认true
     */
    private Boolean removeAbandoned;

    /**
     * 异常连接判断条件 单位：秒 则认为是异常的，需要强行关闭。默认180s
     */
    private Integer removeAbandonedTimeout;

    /**
     * 网络读取超时，网络连接超时, 默认值： connectTimeout=5000;socketTimeout=5000
     */
    private String connectionProperties;

    /**
     * 是否设置自动提交，相当于每个语句一个事务, 默认值：true
     */
    private Boolean defaultAutoCommit;

    /**
     * 记录被判定为异常的连接, 默认值：true
     */
    private Boolean logAbandoned;

    private String connectionInitSqls ;

    private List<Filter> filters = new ArrayList<>();

    private List<String> filterBeans;

    /**
     * 统一资源名称
     */
    private String urn;
    /**
     * 链接闲置归还时长
     */
    private Integer keepAliveBetweenTimeMillis;

    /**
     * 链接是否保活
     */
    private Boolean keepAlive;

    /**
     * 使用非公平锁，建议开启，能大幅提升性能，影响就是不保证公平性
     */
    private Boolean useUnfairLock;


    /**
     * Determine the driver to use based on this configuration and the environment.
     *
     * @return the driver to use
     * @since 1.4.0
     */
    public String determineDriverClassName() {
        if (StringUtils.hasText(this.driverClassName)) {
            return this.driverClassName;
        }
        return "com.mysql.jdbc.Driver";
    }


    public String getDriverClassName() {
        return driverClassName;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (isValidPropertiesBean()) {
            doAfterPropertiesSet();
        }
    }

    private boolean isValidPropertiesBean() {
        return StringUtils.hasText(getUrl());
    }

    private void doAfterPropertiesSet() {
        this.driverClassName = determineDriverClassName();
    }
}