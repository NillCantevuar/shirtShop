package pl.practic.shirtshop.support;

import org.springframework.stereotype.Component;
import pl.practic.shirtshop.entities.Product;

@Component
public class ProductAbility {

    public Product generateOneProduct(){
        Product product = new Product();
        product.setBrand("Croop");
        product.setName("Tropico");
        product.setPrice(5000);
        product.setStock(55);
        product.setType("ENUM");

        return product;
    }
}
