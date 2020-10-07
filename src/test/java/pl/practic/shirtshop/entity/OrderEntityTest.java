package pl.practic.shirtshop.entity;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.repositories.OrderRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderEntityTest {

    @Autowired
    OrderRepository orderRepository;

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
        Assert.assertEquals(status,pulledOrder.getStatus());
    }
    @Test
    @Transactional
    public void shouldSetCustomerIntoOrderAndGetOrder_statusCheck() {
        //given
        Order order = new Order();
        String status= "OK";
        order.setStatus(status);
        Customer customer = new Customer();
        order.setCustomer(customer);
        //when
        orderRepository.save(order);
        Order pulledOrder = orderRepository.getOne(order.getId());
        //then
        Assert.assertEquals(status,pulledOrder.getStatus());
    }
}
