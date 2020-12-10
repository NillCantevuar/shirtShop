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


   public OrderLine generateOneOrderLine(){

        OrderLine orderLine = new OrderLine();

        orderLine.setQuantity(6);
        orderLine.setOrder(null);
        orderLine.setProduct(null);

        return orderLine;

    }
    public OrderLineDTO generateOneOrderLineDTO(){

        OrderLineDTO orderLine = new OrderLineDTO();

        orderLine.setQuantity(6);
        orderLine.setOrderId(null);
        orderLine.setProductId(null);
        return orderLine;

    }
    public OrderLine generateSecondOrderLine(){

        OrderLine orderLine = new OrderLine();

        orderLine.setQuantity(3);
        orderLine.setOrder(null);
        orderLine.setProduct(null);

        return orderLine;

    }
    public OrderLineDTO generateSecondOrderLineDTO(){

        OrderLineDTO orderLine = new OrderLineDTO();

        orderLine.setQuantity(3);
        orderLine.setOrderId(null);
        orderLine.setProductId(null);
        return orderLine;

    }
    public OrderLine generateOneOrderLineWithProductAndOrder(){

        OrderLine orderLine = new OrderLine();

        orderLine.setQuantity(6);
        orderLine.setOrder(orderAbility.generateOneOrder());
        orderLine.setProduct(productAbility.generateOneProduct1());

        return orderLine;

    }
    public OrderLineDTO generateOneOrderLineDTOWithProductAndOrder(){

        OrderLineDTO orderLine = new OrderLineDTO();

        orderLine.setQuantity(6);
        orderLine.setOrderId(orderAbility.generateOneOrder().getId());
        orderLine.setProductId(productAbility.generateOneProduct1().getId());
        return orderLine;

    }
    public OrderLine generateSecondOrderLineWithProductAndOrder(){

        OrderLine orderLine = new OrderLine();

        orderLine.setQuantity(3);
        orderLine.setOrder(orderAbility.generateOneOrder());
        orderLine.setProduct(productAbility.generateOneProduct1());

        return orderLine;

    }
    public OrderLineDTO generateSecondOrderLineDTOWithProductAndOrder(){

        OrderLineDTO orderLine = new OrderLineDTO();

        orderLine.setQuantity(3);
        orderLine.setOrderId(orderAbility.generateOneOrder().getId());
        orderLine.setProductId(productAbility.generateOneProduct1().getId());
        return orderLine;

    }
}
