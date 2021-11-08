package com.druid.demo.configuration;

import com.druid.demo.properties.DruidDataSourceProperties;
import com.druid.demo.utils.DataSourceHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo.configuration
 * @ClassName: DruidDataSourceConfiguration
 * @Description:
 * @Date: 2021/11/8 5:59 下午
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.druid.demo.mapper"})
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class DruidDataSourceConfiguration {

    @Bean
    public DataSource dataSource(DruidDataSourceProperties druidDataSource) {
        return DataSourceHelper.dataSourceOf(druidDataSource);
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mysql/mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager pbbTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
