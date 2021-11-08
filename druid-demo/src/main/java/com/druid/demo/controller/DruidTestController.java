package com.druid.demo.controller;

import com.druid.demo.mapper.TbOrderMapper;
import com.druid.demo.model.TbOrder;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @ProjectName: druid
 * @Package: com.druid.demo.controller
 * @ClassName: DruidTestController
 * @Description:
 * @Date: 2021/11/8 7:53 下午
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test/demo")
public class DruidTestController {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @RequestMapping("/insert")
    public Map<String, Object> insertTest() {
        Map<String, Object> result = Maps.newHashMap();
        tbOrderMapper.insert(buildTbOrder());
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> listTest(Long id) {
        Map<String, Object> result = Maps.newHashMap();
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id);
        result.put("data", tbOrder);
        return result;
    }

    private TbOrder buildTbOrder() {
        Random random = new Random();
        TbOrder tbOrder = new TbOrder();
        //tbOrder.setOrderNo(UUID.randomUUID().toString());
        tbOrder.setOrderNo(UUID.randomUUID().toString());
        tbOrder.setUserId(Long.valueOf(UUID.randomUUID().toString().hashCode()));
        tbOrder.setShopId(random.nextLong());

        tbOrder.setPaymentChannel(1);
        tbOrder.setOrderStatus(2);
        tbOrder.setCreateTime(new Date());
        tbOrder.setPaymentTime(new Date());
        tbOrder.setOrderSn(UUID.randomUUID().toString());
        tbOrder.setGoodsCount(1);
        tbOrder.setGoodsAmount(100L);
        tbOrder.setActualPayAmount(100L);

        tbOrder.setLogisticsFee(0L);
        tbOrder.setAddressId(random.nextLong());
        tbOrder.setDeliveryTime(new Date());
        tbOrder.setCompletedTime(new Date());
        return tbOrder;
    }
}
