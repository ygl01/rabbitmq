package com.ygl.rabbitmq.springbootorderrabbitmqproducer1;

import com.ygl.rabbitmq.springbootorderrabbitmqproducer1.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootOrderRabbitmqProducer1ApplicationTests {
    @Autowired
    OrderService orderService;
    @Test
    void testFanout() {
        orderService.makeOrderFanout("1","1",22);
    }
    @Test
    void testDirect(){
        orderService.makeOrderDirect("1","2",3);
    }
    @Test
    void testTopic(){
        orderService.makeOrderTopic("1","4",8);
    }

}
