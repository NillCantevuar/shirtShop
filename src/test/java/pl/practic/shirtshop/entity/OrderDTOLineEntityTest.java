package pl.practic.shirtshop.entity;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.repositories.OrderLineRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDTOLineEntityTest {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Test
    @Transactional
    public void shouldAddAndGetOrderLineFromDB_quantityCheck() {
        //given
        OrderLine orderLine = new OrderLine();
        int quantity = 3;
        orderLine.setQuantity(quantity);
        //when
        orderLineRepository.save(orderLine);
        OrderLine pulledOrderLine = orderLineRepository.getOne(orderLine.getId());
        //then
        Assert.assertEquals(Optional.of(quantity).get(),pulledOrderLine.getQuantity());

    }
}
