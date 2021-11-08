package com.druid.demo.utils;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.druid.demo.properties.DruidDataSourceProperties;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo.utils
 * @ClassName: DataSourceConfigurationSupport
 * @Description:
 * @Date: 2021/11/8 10:21 下午
 * @Version: 1.0
 */
public class DataSourceConfigurationSupport {

    private DataSourceConfigurationSupport() {

    }

    public static void dataSourceOf(MutablePropertyValues mpv, DruidDataSourceProperties properties, List<Filter> filters) {
        try {
            properties.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!StringUtils.isEmpty(properties.getUrn())){
            mpv.addPropertyValue("urn", properties.getUrn());
        }
        if(!StringUtils.isEmpty(properties.getDriverClassName())){
            mpv.addPropertyValue("driverClassName", properties.getDriverClassName());
        }
        if(!StringUtils.isEmpty(properties.getUrl())){
            mpv.addPropertyValue("url", properties.getUrl());
        }
        if(!StringUtils.isEmpty(properties.getUsername())){
            mpv.addPropertyValue("username", properties.getUsername());
        }
        if(null !=properties.getPassword()){
            mpv.addPropertyValue("password", properties.getPassword());
        }
        if(null != properties.getInitialSize()){
            mpv.addPropertyValue("initialSize", properties.getInitialSize());
        }
        if(null != properties.getMaxActive()){
            mpv.addPropertyValue("maxActive", properties.getMaxActive());
        }
        if(null != properties.getMinIdle()){
            mpv.addPropertyValue("minIdle", properties.getMinIdle());
        }
        if(null != properties.getMaxWait()){
            mpv.addPropertyValue("maxWait", properties.getMaxWait());
        }
        if(null != properties.getPoolPreparedStatements()){
            mpv.addPropertyValue("poolPreparedStatements", properties.getPoolPreparedStatements());
        }
        if(null != properties.getMaxPoolPreparedStatementPerConnectionSize()){
            mpv.addPropertyValue("maxPoolPreparedStatementPerConnectionSize", properties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if(null !=properties.getValidationQuery()){
            mpv.addPropertyValue("validationQuery", properties.getValidationQuery());
        }
        if(null != properties.getValidationQueryTimeout()){
            mpv.addPropertyValue("validationQueryTimeout", properties.getValidationQueryTimeout());
        }
        if(null != properties.getTestOnBorrow()){
            mpv.addPropertyValue("testOnBorrow", properties.getTestOnBorrow());
        }
        if(null != properties.getTestOnReturn()){
            mpv.addPropertyValue("testOnReturn", properties.getTestOnReturn());
        }
        if(null != properties.getTestWhileIdle()){
            mpv.addPropertyValue("testWhileIdle", properties.getTestWhileIdle());
        }
        if(null != properties.getTimeBetweenEvictionRunsMillis()){
            mpv.addPropertyValue("timeBetweenEvictionRunsMillis", properties.getTimeBetweenEvictionRunsMillis());
        }
        if(null != properties.getMinEvictableIdleTimeMillis()){
            mpv.addPropertyValue("minEvictableIdleTimeMillis", properties.getMinEvictableIdleTimeMillis());
        }
        if(null != properties.getMaxEvictableIdleTimeMillis()){
            mpv.addPropertyValue("maxEvictableIdleTimeMillis", properties.getMaxEvictableIdleTimeMillis());
        }
        if(null != properties.getPhyTimeoutMillis()){
            mpv.addPropertyValue("phyTimeoutMillis", properties.getPhyTimeoutMillis());
        }
        if(null != properties.getRemoveAbandoned()){
            mpv.addPropertyValue("removeAbandoned", properties.getRemoveAbandoned());
        }
        if(null != properties.getRemoveAbandonedTimeout()){
            mpv.addPropertyValue("removeAbandonedTimeout", properties.getRemoveAbandonedTimeout());
        }
        if(null !=properties.getConnectionProperties()){
            mpv.addPropertyValue("connectProperties", toProperties(properties.getConnectionProperties()));
        }
        if(null != properties.getDefaultAutoCommit()){
            mpv.addPropertyValue("defaultAutoCommit", properties.getDefaultAutoCommit());
        }
        if(null != properties.getLogAbandoned()){
            mpv.addPropertyValue("logAbandoned", properties.getLogAbandoned());
        }

        if(null != filters && !filters.isEmpty()){
            mpv.addPropertyValue("proxyFilters", filters);
        }

        if(null!=properties.getConnectionInitSqls()){
            StringTokenizer tokenizer = new StringTokenizer(properties.getConnectionInitSqls(), ";");
            mpv.addPropertyValue("connectionInitSqls", Collections.list(tokenizer));
        }

        if(null!=properties.getKeepAliveBetweenTimeMillis()){
            mpv.addPropertyValue("keepAliveBetweenTimeMillis", properties.getKeepAliveBetweenTimeMillis());
        }

        if (null!=properties.getKeepAlive()){
            mpv.add("keepAlive",properties.getKeepAlive());
        }

        if(null != properties.getUseUnfairLock()){
            mpv.addPropertyValue("useUnfairLock", properties.getUseUnfairLock());
        }
    }

    public static DruidDataSource dataSourceOf(DruidDataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();


        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setMaxWait(properties.getMaxWait());

        dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setValidationQuery(properties.getValidationQuery());
        dataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());
        dataSource.setTestOnReturn(properties.getTestOnReturn());
        dataSource.setTestWhileIdle(properties.getTestWhileIdle());

        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());

        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        dataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());

        dataSource.setPhyTimeoutMillis(properties.getPhyTimeoutMillis());

        dataSource.setRemoveAbandoned(properties.getRemoveAbandoned());
        dataSource.setRemoveAbandonedTimeout(properties.getRemoveAbandonedTimeout());

        dataSource.setConnectProperties(toProperties(properties.getConnectionProperties()));
        dataSource.setDefaultAutoCommit(properties.getDefaultAutoCommit());
        dataSource.setLogAbandoned(properties.getLogAbandoned());

        dataSource.setProxyFilters(properties.getFilters());

        StringTokenizer tokenizer = new StringTokenizer(properties.getConnectionInitSqls(), ";");
        dataSource.setConnectionInitSqls(Collections.list(tokenizer));

        dataSource.setUseUnfairLock(properties.getUseUnfairLock());
        return dataSource;
    }

    private static Properties toProperties(String connectionProperties) {
        if (!StringUtils.hasText(connectionProperties)) {
            return null;
        }

        String[] arr = connectionProperties.split(";");
        if (arr.length == 0) {
            return null;
        }

        Properties properties = new Properties();

        for (String entry : arr) {
            String[] s = entry.split("=");
            String key = StringUtils.trimWhitespace(s[0]);
            if (s.length == 2) {
                String value = StringUtils.trimWhitespace(s[1]);
                properties.put(key, value);
            }

        }

        return properties;
    }
}
