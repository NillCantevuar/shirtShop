package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.enums.ProductType;
import pl.practic.shirtshop.repositories.ProductRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductEntityTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    public void shouldAddAndGetProductFromDB_brandTypeCheck() {
        //given
        Product product = new Product();
        String brand = "crop";
        ProductType type = ProductType.TSHIRT;
        product.setBrand(brand);
        product.setType(type);
        //when
        productRepository.save(product);
        Product pulledProduct = productRepository.getOne(product.getId());
        //then
        Assert.assertEquals(brand,pulledProduct.getBrand());
        Assert.assertEquals(type,pulledProduct.getType());

    }

}
