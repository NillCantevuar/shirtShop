package pl.practic.shirtshop.entity;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderEntityTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional
    public void shouldAddAndGetOneOrderFromDB_statusCheck() {
        //given
        Order order = new Order();
        String status = "Send";
        order.setStatus(status);
        //when
        orderRepository.save(order);
        Order pulledOrder = orderRepository.getOne(order.getId());
        //then
        Assert.assertEquals(status, pulledOrder.getStatus());
    }

    @Test
    @Transactional
    public void shouldSetCustomerIntoOrderAndGetOrder_statusCheck() {
        //given
        Order order = new Order();
        String status = "OK";
        order.setStatus(status);
        Customer customer = new Customer();
        order.setCustomer(customer);
        //when
        orderRepository.save(order);
        Order pulledOrder = orderRepository.getOne(order.getId());
        //then
        Assert.assertEquals(status, pulledOrder.getStatus());
    }


    @Test
    @Transactional
    public void shouldSetMultipleOrdersIntoCustomer_statusCheck(){
        //given
        Order order1 = new Order();
        String status = "OK";
        order1.setStatus(status);
        Order order2 = new Order();
        String status2 = "SEND";
        order2.setStatus(status2);
        Order order3 = new Order();
        String status3 = "WAIT";
        order3.setStatus(status3);
        Customer customer = new Customer();
        customer.setOrder(new ArrayList<Order>(Arrays.asList(order1,order2,order3)));

        //when
        customerRepository.save(customer);
        Customer pulledCustomer = customerRepository.getOne(customer.getId());
        //then
        Assert.assertEquals(status, pulledCustomer.getOrder().get(0).getStatus());
        Assert.assertEquals(status2,pulledCustomer.getOrder().get(1).getStatus());
        Assert.assertEquals(status3,pulledCustomer.getOrder().get(2).getStatus());
    }
}
