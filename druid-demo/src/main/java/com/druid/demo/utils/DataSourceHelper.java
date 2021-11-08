package com.druid.demo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.druid.demo.properties.DruidDataSourceProperties;
import org.springframework.util.StringUtils;

import java.util.Collections;
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
public abstract class DataSourceHelper {


    public static DruidDataSource dataSourceOf(DruidDataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();


        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
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

        dataSource.setProxyFilters(properties.getProxyFilters());

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
