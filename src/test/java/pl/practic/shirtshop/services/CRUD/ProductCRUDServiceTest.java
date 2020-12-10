package pl.practic.shirtshop.services.CRUD;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
       // OrderLineDTO orderLineDTO  =orderLineAbility.
    }

    @Test
    @Transactional
    public void shouldUpdateProduct_CompareFields_U() {

    }

    @Test
    @Transactional
    public void shouldUpdateProductWithOrderLine_CompareFields_U() {

    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteProductUsignId_isNull_D() {


        //TODO OrderLine się nie powinien usuwać
    }

}
