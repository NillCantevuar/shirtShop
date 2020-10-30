package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderLineRepository;
import pl.practic.shirtshop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCRUDService implements CRUDService<OrderDTO> {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DTOMapper dtoMapper;

    @Override
    public OrderDTO find(Integer id) {
        return orderRepository.getOne(id).toDTO();

    }

    @Override
    public Integer save(OrderDTO orderDTO) {
        Order saved = orderRepository.save(dtoMapper.fromOrderDTO(orderDTO));
        return saved.getId();
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, Integer id) {
         Order pulledOrder = orderRepository.getOne(id);
        pulledOrder.setDateTime(orderDTO.getDateTime());
        pulledOrder.setStatus(orderDTO.getStatus());
        pulledOrder.setCustomer(customerRepository.getOne(orderDTO.getId()));
        pulledOrder.setOrderLines(fillOrderLinesList(orderDTO.getOrderLinesId()));
        Order saved = orderRepository.save(pulledOrder);
        return saved.toDTO();
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
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
