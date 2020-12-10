package pl.practic.shirtshop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.entities.Order;

import java.time.LocalDateTime;

@Component
public class OrderAbility {

    @Autowired
    CustomerAbility customerAbility;

    public Order generateOneOrder(){

        Order order = new Order();
        order.setStatus("SEND");
        order.setCustomer(null);
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public OrderDTO generateOneOrderDTO(){

        OrderDTO order = new OrderDTO();
        order.setStatus("SEND");
        order.setCustomerId(null);
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public Order generateSecondOrder(){

        Order order = new Order();
        order.setStatus("OK");
        order.setCustomer(null);
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public OrderDTO generateSecondOrderDTO(){

        OrderDTO order = new OrderDTO();
        order.setStatus("OK");
        order.setCustomerId(null);
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public Order generateOneOrderWithCustomer(){

        Order order = new Order();
        order.setStatus("SEND");
        order.setCustomer(customerAbility.generateOneCustomer());
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public OrderDTO generateOneOrderDTOWithCustomer(){

        OrderDTO order = new OrderDTO();
        order.setStatus("SEND");
        order.setCustomerId(customerAbility.generateOneCustomer().getId());
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public Order generateSecondOrderWithCustomer(){

        Order order = new Order();
        order.setStatus("OK");
        order.setCustomer(customerAbility.generateOneCustomer());
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }
    public OrderDTO generateSecondOrderDTOWithCustomer(){

        OrderDTO order = new OrderDTO();
        order.setStatus("OK");
        order.setCustomerId(customerAbility.generateOneCustomer().getId());
        order.setDateTime(LocalDateTime.MIN);

        return order;
    }

}
