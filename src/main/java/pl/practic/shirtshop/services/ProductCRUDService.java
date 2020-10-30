package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.enums.ProductType;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.OrderLineRepository;
import pl.practic.shirtshop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCRUDService implements CRUDService<ProductDTO> {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

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
    public ProductDTO update(ProductDTO productDTO, int id) {
        Product pulledProduct = productRepository.getOne(id);
        pulledProduct.setName(productDTO.getName());
        pulledProduct.setStock(productDTO.getStock());
        pulledProduct.setPrice(productDTO.getPrice());
        pulledProduct.setBrand(productDTO.getBrand());
        pulledProduct.setType(ProductType.valueOf(productDTO.getType()));
        pulledProduct.setOrderLine(fillOrderLinesList(productDTO.getOrderLineId()));
        Product saved = productRepository.save(pulledProduct);
        return saved.toDTO();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    private List<OrderLine> fillOrderLinesList(List<Integer> indexes) {
        List<OrderLine> orderLines = new ArrayList<>();

        for (Integer i : indexes
        ) {
            orderLines.add(orderLineRepository.getOne(i));

        }
        return orderLines;
    }
}
