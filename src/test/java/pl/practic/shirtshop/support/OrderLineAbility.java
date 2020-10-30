package pl.practic.shirtshop.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.entities.OrderLine;

@Component
public class OrderLineAbility {

    @Autowired
    OrderAbility orderAbility;
    @Autowired
    ProductAbility productAbility;


    OrderLine generateOneOrderLine(){

        OrderLine orderLine = new OrderLine();

        orderLine.setQuantity(6);
        orderLine.setOrder(orderAbility.generateOneOrder());
        orderLine.setProduct(productAbility.generateOneProduct1());

        return orderLine;

    }
    OrderLineDTO generateOneOrderLineDTO(){

        OrderLineDTO orderLine = new OrderLineDTO();

        orderLine.setQuantity(6);
        orderLine.setOrderId(orderAbility.generateOneOrder().getId());
        orderLine.setProductId(productAbility.generateOneProduct1().getId());
        return orderLine;

    }
}
