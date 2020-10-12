package pl.practic.shirtshop.support;

import org.springframework.stereotype.Component;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.enums.ProductType;

@Component
public class ProductAbility {

    public Product generateOneProduct1(){
        Product product = new Product();
        product.setBrand("Croop");
        product.setName("Tropico");
        product.setPrice(5000);
        product.setStock(55);
        product.setType(ProductType.TSHIRT);

        return product;
    }
    public Product generateOneProduct2(){
        Product product = new Product();
        product.setBrand("Vans");
        product.setName("Warmy");
        product.setPrice(50000);
        product.setStock(11);
        product.setType(ProductType.BLOUSE);

        return product;
    }
    public Product generateOneProduct3(){
        Product product = new Product();
        product.setBrand("Swanson");
        product.setName("Leather-B20");
        product.setPrice(10000);
        product.setStock(97);
        product.setType(ProductType.BELT);

        return product;
    }
}
