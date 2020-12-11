package pl.practic.shirtshop.services.CRUD;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.services.OrderCRUDService;
import pl.practic.shirtshop.services.OrderLineCRUDService;
import pl.practic.shirtshop.services.ProductCRUDService;
import pl.practic.shirtshop.support.OrderAbility;
import pl.practic.shirtshop.support.OrderLineAbility;
import pl.practic.shirtshop.support.ProductAbility;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderLineCRUDServiceTest {

    @Autowired
    OrderCRUDService orderCRUDService;
    @Autowired
    OrderLineCRUDService orderLineCRUDService;
    @Autowired
    ProductCRUDService productCRUDService;
    @Autowired
    OrderLineAbility orderLineAbility;
    @Autowired
    OrderAbility orderAbility;
    @Autowired
    ProductAbility productAbility;

    @Test
    @Transactional
    public void shouldSaveAndFindOrderLineAloneUsignId_CompareFields_CR() {
        //given
        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        //when
        Integer savedId = orderLineCRUDService.save(orderLineDTO);
        OrderLineDTO pulledOrderLine = orderLineCRUDService.find(savedId);
        //then
        Assert.assertEquals(orderLineDTO.getQuantity(), pulledOrderLine.getQuantity());

    }

    @Test
    @Transactional
    public void shouldSaveAndFindOrderLineWithOrderUsignId_CompareFields_CR() {
        //given
        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        Integer savedOrderId = orderCRUDService.save(orderDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        orderLineDTO.setOrderId(savedOrderId);
        //when
        Integer savedId = orderLineCRUDService.save(orderLineDTO);
        OrderLineDTO pulledOrderLine = orderLineCRUDService.find(savedId);
        //then
        Assert.assertEquals(orderLineDTO.getQuantity(), pulledOrderLine.getQuantity());
        Assert.assertEquals(orderCRUDService.find(orderLineDTO.getOrderId()).getDateTime(),
                orderCRUDService.find(pulledOrderLine.getOrderId()).getDateTime());
    }

    @Test
    @Transactional
    public void shouldSaveAndFindOrderLineWithProductUsignId_CompareFields_CR() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer savedProductId = productCRUDService.save(productDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        orderLineDTO.setProductId(savedProductId);
        //when
        Integer savedId = orderLineCRUDService.save(orderLineDTO);
        OrderLineDTO pulledOrderLine = orderLineCRUDService.find(savedId);
        //then
        Assert.assertEquals(orderLineDTO.getQuantity(), pulledOrderLine.getQuantity());
        Assert.assertEquals(productCRUDService.find(orderLineDTO.getProductId()).getType(),
                productCRUDService.find(pulledOrderLine.getProductId()).getType());
    }

    @Test
    @Transactional
    public void shouldSaveAndFindOrderLineWithProductAndOrderUsignId_CompareFields_CR() {
        //given
        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        Integer savedOrderId = orderCRUDService.save(orderDTO);

        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer savedProductId = productCRUDService.save(productDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        orderLineDTO.setProductId(savedProductId);
        orderLineDTO.setOrderId(savedOrderId);
        //when
        Integer savedId = orderLineCRUDService.save(orderLineDTO);
        OrderLineDTO pulledOrderLine = orderLineCRUDService.find(savedId);
        //then
        Assert.assertEquals(orderLineDTO.getQuantity(), pulledOrderLine.getQuantity());
        Assert.assertEquals(orderCRUDService.find(orderLineDTO.getOrderId()).getDateTime(),
                orderCRUDService.find(pulledOrderLine.getOrderId()).getDateTime());
        Assert.assertEquals(productCRUDService.find(orderLineDTO.getProductId()).getType(),
                productCRUDService.find(pulledOrderLine.getProductId()).getType());


    }

    @Test
    @Transactional
    public void shouldUpdateOrderLine_CompareFields_U() {
        //given
        OrderLineDTO originalOrderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        OrderLineDTO updatingOrderLine = orderLineAbility.generateSecondOrderLineDTO();
        Integer savedOriginalOrderLineId = orderLineCRUDService.save(originalOrderLineDTO);
        //when
        orderLineCRUDService.update(updatingOrderLine,savedOriginalOrderLineId);
        OrderLineDTO pulledOrderLineDTO = orderLineCRUDService.find(savedOriginalOrderLineId);
        //then
        Assert.assertEquals(updatingOrderLine.getQuantity(),pulledOrderLineDTO.getQuantity());

    }

    @Test
    @Transactional
    public void shouldUpdateOrderLineWithOrder_CompareFields_U() {
        //given
        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        Integer savedOriginalOrderDTOId = orderCRUDService.save(orderDTO);

        OrderLineDTO originalOrderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        originalOrderLineDTO.setOrderId(savedOriginalOrderDTOId);

        Integer savedOriginalOrderLineId = orderLineCRUDService.save(originalOrderLineDTO);

        OrderDTO updatingOrderDTO = orderAbility.generateSecondOrderDTO();
        Integer savedUpdatingOrderDTOId = orderCRUDService.save(updatingOrderDTO);

        OrderLineDTO updatingOrderLine = orderLineAbility.generateSecondOrderLineDTO();
        updatingOrderLine.setOrderId(savedUpdatingOrderDTOId);
        //when
        orderLineCRUDService.update(updatingOrderLine,savedOriginalOrderLineId);
        OrderLineDTO pulledOrderLineDTO = orderLineCRUDService.find(savedOriginalOrderLineId);
        //then
        Assert.assertEquals(updatingOrderLine.getQuantity(),pulledOrderLineDTO.getQuantity());
        Assert.assertEquals(orderCRUDService.find(updatingOrderLine.getOrderId()).getDateTime(),
                orderCRUDService.find(pulledOrderLineDTO.getOrderId()).getDateTime());

    }

    @Test
    @Transactional
    public void shouldUpdateOrderLineWithProduct_CompareFields_U() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer savedOriginalProductId = productCRUDService.save(productDTO);

        OrderLineDTO originalOrderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        originalOrderLineDTO.setProductId(savedOriginalProductId);

        Integer savedOriginalOrderLineId = orderLineCRUDService.save(originalOrderLineDTO);

        ProductDTO updatingProductDto = productAbility.generateOneProduct2DTO();
        Integer savedUpdatingProducyDTOId = productCRUDService.save(updatingProductDto);

        OrderLineDTO updatingOrderLine = orderLineAbility.generateSecondOrderLineDTO();
        updatingOrderLine.setProductId(savedUpdatingProducyDTOId);
        //when
        orderLineCRUDService.update(updatingOrderLine, savedOriginalOrderLineId);
        OrderLineDTO pulledOrderLineDTO = orderLineCRUDService.find(savedOriginalOrderLineId);
        //then
        Assert.assertEquals(updatingOrderLine.getQuantity(), pulledOrderLineDTO.getQuantity());
        Assert.assertEquals(productCRUDService.find(updatingOrderLine.getProductId()).getType(),
                productCRUDService.find(pulledOrderLineDTO.getProductId()).getType());
    }

    @Test
    @Transactional
    public void shouldUpdateOrderLineWithOrderAndProduct_CompareFields_U() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer savedOriginalProductId = productCRUDService.save(productDTO);
        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        Integer savedOriginalOrderDTOId = orderCRUDService.save(orderDTO);

        OrderLineDTO originalOrderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        originalOrderLineDTO.setProductId(savedOriginalProductId);
        originalOrderLineDTO.setOrderId(savedOriginalOrderDTOId);
        Integer savedOriginalOrderLineId = orderLineCRUDService.save(originalOrderLineDTO);

        ProductDTO updatingProductDto = productAbility.generateOneProduct2DTO();
        Integer savedUpdatingProducyDTOId = productCRUDService.save(updatingProductDto);
        OrderDTO updatingOrderDTO = orderAbility.generateSecondOrderDTO();
        Integer savedUpdatingOrderDTOId = orderCRUDService.save(updatingOrderDTO);

        OrderLineDTO updatingOrderLine = orderLineAbility.generateSecondOrderLineDTO();
        updatingOrderLine.setProductId(savedUpdatingProducyDTOId);
        updatingOrderLine.setOrderId(savedUpdatingOrderDTOId);
        //when
        orderLineCRUDService.update(updatingOrderLine, savedOriginalOrderLineId);
        OrderLineDTO pulledOrderLineDTO = orderLineCRUDService.find(savedOriginalOrderLineId);
        //then
        Assert.assertEquals(updatingOrderLine.getQuantity(), pulledOrderLineDTO.getQuantity());
        Assert.assertEquals(productCRUDService.find(updatingOrderLine.getProductId()).getType(),
                productCRUDService.find(pulledOrderLineDTO.getProductId()).getType());
        Assert.assertEquals(orderCRUDService.find(updatingOrderLine.getOrderId()).getDateTime(),
                orderCRUDService.find(pulledOrderLineDTO.getOrderId()).getDateTime());


    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteOrderLineUsignId_isNull_D() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer savedProductDTOId = productCRUDService.save(productDTO);
        OrderDTO orderDTO = orderAbility.generateOneOrderDTO();
        Integer savedOrderDTOId = orderCRUDService.save(orderDTO);

        OrderLineDTO orderLineDTO = orderLineAbility.generateOneOrderLineDTO();
        orderLineDTO.setProductId(savedProductDTOId);
        orderLineDTO.setOrderId(savedOrderDTOId);
        Integer savedOrderLineId = orderLineCRUDService.save(orderLineDTO);
        //when
        orderLineCRUDService.delete(savedOrderLineId);
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class,()->orderLineCRUDService.find(savedOrderLineId));


    }


}
