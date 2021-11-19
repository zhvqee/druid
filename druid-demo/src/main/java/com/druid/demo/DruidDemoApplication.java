package com.druid.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import com.druid.demo.utils.BeanContext;
import com.mysql.jdbc.JDBC4Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo
 * @ClassName: DruidDemoApnplication
 * @Description:
 * @Date: 2021/11/8 7:52 下午
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.druid.demo"})
public class DruidDemoApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DruidDemoApplication.class, args);

        DruidDataSource druidDataSource = BeanContext.getBean(DruidDataSource.class);
        DruidPooledConnection connection = druidDataSource.getConnection();
        ConnectionProxy unwrap = connection.unwrap(ConnectionProxy.class);
        System.out.println(unwrap);

        JDBC4Connection jdbc4Connection = connection.unwrap(JDBC4Connection.class);
        System.out.println(jdbc4Connection);
    }
}
