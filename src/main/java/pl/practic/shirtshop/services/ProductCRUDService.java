package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.enums.ProductType;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.ProductRepository;

@Service
public class ProductCRUDService implements CRUDService<ProductDTO> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDTO find(int id) {
        return productRepository.getOne(id).toDTO();
    }

    @Override
    public int save(ProductDTO productDTO) {
         Product saved = productRepository.save(Product.fromDTO(productDTO));
        return saved.getId();
    }

    @Override
    public int update(ProductDTO productDTO, int id) {
        Product pulledProduct = productRepository.getOne(id);
        pulledProduct.setName(productDTO.getName());
        pulledProduct.setStock(productDTO.getStock());
        pulledProduct.setPrice(productDTO.getPrice());
        pulledProduct.setBrand(productDTO.getBrand());
        pulledProduct.setType(ProductType.valueOf(productDTO.getType()));
        //todo
        pulledProduct.setOrderLine(null);
        //todo
        return 0;
    }

    @Override
    public void delete(int id) {
    productRepository.deleteById(id);
    }
}
