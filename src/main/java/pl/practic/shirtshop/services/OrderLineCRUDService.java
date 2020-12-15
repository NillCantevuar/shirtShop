package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.entities.Product;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.OrderLineRepository;
import pl.practic.shirtshop.repositories.OrderRepository;
import pl.practic.shirtshop.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderLineDTO> findAll() {
        return orderLineRepository.findAll().stream().map(OrderLine::toDTO).collect(Collectors.toList());
    }

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

        Order order = null;
        Product product = null;

        if (orderLineDTO.getOrderId()!= null){
            order =orderRepository.getOne(orderLineDTO.getOrderId());
        }
        if (orderLineDTO.getProductId() !=null){
            product = productRepository.getOne(orderLineDTO.getProductId());
        }

        OrderLine pulledOrderLine = orderLineRepository.getOne(id);
        pulledOrderLine.setQuantity(orderLineDTO.getQuantity());
        pulledOrderLine.setOrder(order);
        pulledOrderLine.setProduct(product);
        orderLineRepository.save(pulledOrderLine);
        return id;
    }

    @Override
    public void delete(Integer id) {
        orderLineRepository.deleteById(id);
    }
}
