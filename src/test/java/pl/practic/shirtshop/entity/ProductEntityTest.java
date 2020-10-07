package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.repositories.ProductRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductEntityTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    public void shouldAddAndGetProductFromDB_brandCheck() {
        //given
        Product product = new Product();
        String brand = "crop";
        product.setBrand(brand);
        //when
        productRepository.save(product);
        Product pulledProduct = productRepository.getOne(product.getId());
        //then
        Assert.assertEquals(brand,pulledProduct.getBrand());

    }

}
