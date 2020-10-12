package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.repositories.OrderLineRepository;
import pl.practic.shirtshop.support.OrderAbility;
import pl.practic.shirtshop.support.ProductAbility;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IntegralEntityTest {

    @Autowired
    OrderAbility orderAbility;

    @Autowired
    ProductAbility productAbility;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Test
    @Transactional
    public void ConsumerOrderedThreDiffrentProducts(){
        //given
        Order completeOrder = orderAbility.generateOneOrder();
        Product product1 = productAbility.generateOneProduct1();
        Product product2 = productAbility.generateOneProduct2();
        Product product3 = productAbility.generateOneProduct3();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setProduct(product1);
        orderLine1.setOrder(completeOrder);
        orderLine1.setQuantity(2);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setProduct(product2);
        orderLine2.setOrder(completeOrder);
        orderLine2.setQuantity(1);

        OrderLine orderLine3 = new OrderLine();
        orderLine3.setProduct(product3);
        orderLine3.setOrder(completeOrder);
        orderLine3.setQuantity(5);
        //when
        orderLineRepository.save(orderLine1);
        orderLineRepository.save(orderLine2);
        orderLineRepository.save(orderLine3);

        OrderLine pulledOrderLine1 = orderLineRepository.getOne(orderLine1.getId());
        OrderLine pulledOrderLine2 = orderLineRepository.getOne(orderLine2.getId());
        OrderLine pulledOrderLine3 = orderLineRepository.getOne(orderLine3.getId());

        //then
        Assert.assertEquals(product1.getBrand(),pulledOrderLine1.getProduct().getBrand());
        Assert.assertEquals(completeOrder.getCustomer().getAdress().getHouseNumber(),"12B");
        Assert.assertEquals(product2.getPrice(),pulledOrderLine2.getProduct().getPrice());
        Assert.assertEquals(product3.getStock(),pulledOrderLine3.getProduct().getStock());

        //numer mieszkania na podstawie orderu
        //nazwa produktu
        //nazwisko n apodstawie contact

        //gdzie znalezc id ?




    }

}
