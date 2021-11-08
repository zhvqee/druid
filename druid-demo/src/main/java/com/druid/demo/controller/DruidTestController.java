package com.druid.demo.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo.controller
 * @ClassName: DruidTestController
 * @Description:
 * @Date: 2021/11/8 7:53 下午
 * @Version: 1.0
 */
@RestController
@RequestMapping("/druid/demo")
public class DruidTestController {

    @RequestMapping("/insert")
    public Map<String, Object> insertTest() {
        Map<String, Object> result = Maps.newHashMap();
        return result;
    }

    @RequestMapping("/listTest")
    public Map<String, Object> listTest() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("data", "123");
        return result;
    }
}
