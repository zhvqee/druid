package com.druid.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanContext implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private static BeanContext self;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        self = this;
    }


    public static <T> T getBean(Class<T> clzz) {
        return self.applicationContext.getBean(clzz);
    }
}
