package pl.practic.shirtshop.services.CRUD;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.services.CustomerCRUDService;
import pl.practic.shirtshop.services.OrderCRUDService;
import pl.practic.shirtshop.services.OrderLineCRUDService;
import pl.practic.shirtshop.support.CustomerAbility;
import pl.practic.shirtshop.support.OrderAbility;
import pl.practic.shirtshop.support.OrderLineAbility;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderCrudServiceTest {
    @Autowired
    OrderCRUDService orderCRUDService;
    @Autowired
    OrderLineCRUDService orderLineCRUDService;
    @Autowired
    CustomerCRUDService customerCRUDService;
    @Autowired
    OrderLineAbility orderLineAbility;
    @Autowired
    OrderAbility orderAbility;
    @Autowired
    CustomerAbility customerAbility;

    @Test
    @Transactional
    public void shouldSaveAndFindOrderLineWithProductAndOrderUsignId_CompareFields_CR() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customerDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        Integer savedOrderLineId = orderLineCRUDService.save(orderLineDTO);

        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        orderDTO.setOrderLinesId(Collections.singletonList(savedOrderLineId));
        orderDTO.setCustomerId(savedCustomerId);
        //when
        Integer savedId = orderCRUDService.save(orderDTO);
        OrderDTO pulledOrder = orderCRUDService.find(savedId);
        //then
        Assert.assertEquals(orderDTO.getStatus(), pulledOrder.getStatus());
        Assert.assertEquals(orderLineCRUDService.find(orderDTO.getOrderLinesId().get(0)).getQuantity(),
                orderLineCRUDService.find(pulledOrder.getOrderLinesId().get(0)).getQuantity());
        Assert.assertEquals(customerCRUDService.find(orderDTO.getCustomerId()).getFirstName(),
                customerCRUDService.find(pulledOrder.getCustomerId()).getFirstName());


    }
    @Test
    @Transactional
    public void shouldUpdateOrderLineWithOrderAndProduct_CompareFields_U() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customerDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        Integer savedOrderLineId = orderLineCRUDService.save(orderLineDTO);

        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        orderDTO.setOrderLinesId(Collections.singletonList(savedOrderLineId));
        orderDTO.setCustomerId(savedCustomerId);
        Integer savedOriginalOrderId = orderCRUDService.save(orderDTO);

        CustomerDTO updatingCustomerDTO = customerAbility.generateSecondCustomerDTO();
        Integer savedUpdatingCustomerId = customerCRUDService.save(updatingCustomerDTO);

        OrderLineDTO updatingOrderLineDTO = orderLineAbility.generateSecondOrderLineDTO();
        Integer savedUpdatingOrderLineId = orderLineCRUDService.save(updatingOrderLineDTO);

        OrderDTO updatingOrder = orderAbility.generateSecondOrderDTO();
        updatingOrder.setCustomerId(savedUpdatingCustomerId);
        updatingOrder.setOrderLinesId(Collections.singletonList(savedUpdatingOrderLineId));
        //when
        orderCRUDService.update(updatingOrder, savedOriginalOrderId);
        OrderDTO pulledOrderDTO = orderCRUDService.find(savedOriginalOrderId);
        //then
        Assert.assertEquals(orderDTO.getStatus(), pulledOrderDTO.getStatus());
        Assert.assertEquals(orderLineCRUDService.find(orderDTO.getOrderLinesId().get(0)).getQuantity(),
                orderLineCRUDService.find(pulledOrderDTO.getOrderLinesId().get(0)).getQuantity());
        Assert.assertEquals(customerCRUDService.find(orderDTO.getCustomerId()).getFirstName(),
                customerCRUDService.find(pulledOrderDTO.getCustomerId()).getFirstName());



    }
    @Test
    @Transactional
    public void shouldSaveAndDeleteOrderLineUsignId_isNull_D() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customerDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        Integer savedOrderLineId = orderLineCRUDService.save(orderLineDTO);

        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        orderDTO.setOrderLinesId(Collections.singletonList(savedOrderLineId));
        orderDTO.setCustomerId(savedCustomerId);
        Integer savedId = orderCRUDService.save(orderDTO);
        //when
        orderLineCRUDService.delete(savedId);
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class,()->orderLineCRUDService.find(savedOrderLineId));


    }
}
