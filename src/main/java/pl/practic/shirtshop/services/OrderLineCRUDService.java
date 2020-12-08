package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.OrderLineRepository;
import pl.practic.shirtshop.repositories.OrderRepository;
import pl.practic.shirtshop.repositories.ProductRepository;

@Service
public class OrderLineCRUDService implements CRUDService<OrderLineDTO> {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DTOMapper dtoMapper;

    @Override
    public OrderLineDTO find(Integer id) {
        return orderLineRepository.getOne(id).toDTO();
    }

    @Override
    public Integer save(OrderLineDTO orderLineDTO) {
        OrderLine saved = orderLineRepository.save(dtoMapper.fromOrderLineDTO(orderLineDTO));
        return saved.getId();
    }

    @Override
    public Integer update(OrderLineDTO orderLineDTO, Integer id) {
        OrderLine pulledOrderLine = orderLineRepository.getOne(id);
        pulledOrderLine.setQuantity(orderLineDTO.getQuantity());
        pulledOrderLine.setOrder(orderRepository.getOne(orderLineDTO.getOrderId()));
        pulledOrderLine.setProduct(productRepository.getOne(orderLineDTO.getProductId()));
        orderLineRepository.save(pulledOrderLine);
        return id;
    }

    @Override
    public void delete(Integer id) {
        orderLineRepository.deleteById(id);
    }
}
