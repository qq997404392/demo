package com.springcloud.test;

import com.springcloud.order.OrderApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderTestApp.class)
@Import(OrderApp.class)
public class OrderTestApp {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Test
    public void test() {
        String serviceId = "app-item";
        for (int i = 0; i < 100; i++) {
            ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
            System.out.println("第" + (i + 1) + "次：" + serviceInstance.getHost() + ": " + serviceInstance.getPort());
        }
    }

}
