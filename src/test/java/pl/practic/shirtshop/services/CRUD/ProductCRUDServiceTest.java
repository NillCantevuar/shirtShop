package pl.practic.shirtshop.services.CRUD;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.services.OrderLineCRUDService;
import pl.practic.shirtshop.services.ProductCRUDService;
import pl.practic.shirtshop.support.OrderLineAbility;
import pl.practic.shirtshop.support.ProductAbility;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCRUDServiceTest {

    @Autowired
    ProductCRUDService productCRUDService;
    @Autowired
    OrderLineCRUDService orderLineCRUDService;
    @Autowired
    DTOMapper dtoMapper;
    @Autowired
    ProductAbility productAbility;
    @Autowired
    OrderLineAbility orderLineAbility;


    @Test
    @Transactional
    public void shouldSaveAndFindProductUsignId_CompareFields_CR() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        //when
        Integer idOfSavedProduct = productCRUDService.save(productDTO);
        ProductDTO pulledProductDTO = productCRUDService.find(idOfSavedProduct);
        //then
        Assert.assertEquals(productDTO.getBrand(),pulledProductDTO.getBrand());
        Assert.assertEquals(productDTO.getType(),pulledProductDTO.getType());

    }

    @Test
    @Transactional
    public void shouldSaveAndFindProductUsignIdWithOrderLine_CompareFields_CR() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        OrderLineDTO orderLineDTO  =orderLineAbility.generateOneOrderLineDTO();
        Integer savedOrderLineId = orderLineCRUDService.save(orderLineDTO);
        productDTO.setOrderLinesId(new ArrayList<Integer>(Arrays.asList(savedOrderLineId)) {
        });
        //when
        Integer savedProductId = productCRUDService.save(productDTO);
        ProductDTO pulledProductDTO = productCRUDService.find(savedProductId);
        //then
        Assert.assertEquals(productDTO.getBrand(),pulledProductDTO.getBrand());
        Assert.assertEquals(productDTO.getType(),pulledProductDTO.getType());
        Assert.assertEquals(productDTO.getOrderLinesId().get(0),pulledProductDTO.getOrderLinesId().get(0));

    }

    @Test
    @Transactional
    public void shouldUpdateProduct_CompareFields_U() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer savedProductId = productCRUDService.save(productDTO);
        ProductDTO updatingProductDTO = productAbility.generateOneProduct2DTO();
        //when
        Integer updatedProductId = productCRUDService.update(updatingProductDTO,savedProductId);
        ProductDTO pulledProductDTO = productCRUDService.find(savedProductId);
        //then
        Assert.assertEquals(updatingProductDTO.getBrand(),pulledProductDTO.getBrand());
        Assert.assertEquals(updatingProductDTO.getType(),pulledProductDTO.getType());

    }

    @Test
    @Transactional
    public void shouldUpdateProductWithOrderLine_CompareFields_U() {
        //given
        OrderLineDTO orderLineDTO  =orderLineAbility.generateOneOrderLineDTO();
        OrderLineDTO updatingOrderLineDTO  =orderLineAbility.generateSecondOrderLineDTO();

        Integer savedOrderLineId = orderLineCRUDService.save(orderLineDTO);
        Integer updatingOrderLineId = orderLineCRUDService.save(orderLineDTO);

        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        productDTO.setOrderLinesId(Collections.singletonList(savedOrderLineId));
        Integer savedProductId = productCRUDService.save(productDTO);


        ProductDTO updatingProductDTO = productAbility.generateOneProduct2DTO();
        updatingProductDTO.setOrderLinesId(Collections.singletonList(updatingOrderLineId));

        //when
        Integer updatedProductId = productCRUDService.update(updatingProductDTO,savedProductId);
        ProductDTO pulledProductDTO = productCRUDService.find(savedProductId);
        //then
        Assert.assertEquals(updatingProductDTO.getBrand(),pulledProductDTO.getBrand());
        Assert.assertEquals(updatingProductDTO.getType(),pulledProductDTO.getType());
        Assert.assertEquals(updatingProductDTO.getOrderLinesId().get(0),pulledProductDTO.getOrderLinesId().get(0));


    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteProductUsignId_isNull_D() {
        //given
        ProductDTO productDTO = productAbility.generateOneProduct1DTO();
        Integer idOfSavedProduct = productCRUDService.save(productDTO);
        //when
        productCRUDService.delete(idOfSavedProduct);
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class, () -> productCRUDService.find(idOfSavedProduct));
        //TODO OrderLine się nie powinien usuwać
    }

}
