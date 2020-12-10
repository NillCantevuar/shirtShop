package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.enums.ProductType;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
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

    @Autowired
    DTOMapper dtoMapper;

    @Override
    public ProductDTO find(Integer id) {
        return productRepository.getOne(id).toDTO();
    }

    @Override
    public Integer save(ProductDTO productDTO) {
        Product saved = productRepository.save(dtoMapper.fromProductDTO(productDTO));
        return saved.getId();
    }

    @Override
    public Integer update(ProductDTO productDTO, Integer id) {

        if (productDTO.getOrderLinesId() != null) {
            if (!productDTO.getOrderLinesId().isEmpty()) {

                Product pulledProduct = productRepository.getOne(id);
                pulledProduct.setName(productDTO.getName());
                pulledProduct.setStock(productDTO.getStock());
                pulledProduct.setPrice(productDTO.getPrice());
                pulledProduct.setBrand(productDTO.getBrand());
                pulledProduct.setType(ProductType.valueOf(productDTO.getType()));
                pulledProduct.setOrderLines(fillOrderLinesList(productDTO.getOrderLinesId()));
                productRepository.save(pulledProduct);
                return id;
            }
        }
        Product pulledProduct = productRepository.getOne(id);
        pulledProduct.setName(productDTO.getName());
        pulledProduct.setStock(productDTO.getStock());
        pulledProduct.setPrice(productDTO.getPrice());
        pulledProduct.setBrand(productDTO.getBrand());
        pulledProduct.setType(ProductType.valueOf(productDTO.getType()));
        pulledProduct.setOrderLines(null);
        productRepository.save(pulledProduct);
        return id;

    }

    @Override
    public void delete(Integer id) {
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
