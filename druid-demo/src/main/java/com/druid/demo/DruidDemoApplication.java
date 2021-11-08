package com.druid.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo
 * @ClassName: DruidDemoApplication
 * @Description:
 * @Date: 2021/11/8 7:52 下午
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.druid.demo"})
public class DruidDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DruidDemoApplication.class, args);
    }
}
