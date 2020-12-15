package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.entities.Customer;
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
    public Integer update(OrderDTO orderDTO, Integer id) {
        Customer customer = null;
        List<OrderLine> orderLines = null;
        Order pulledOrder = orderRepository.getOne(id);

        if (orderDTO.getCustomerId() != null){
            customer = customerRepository.getOne(orderDTO.getCustomerId());
        }
        if(orderDTO.getOrderLinesId() != null){
            if (!orderDTO.getOrderLinesId().isEmpty()){
                orderLines = fillOrderLinesList(orderDTO.getOrderLinesId());
            }
        }

        pulledOrder.setDateTime(orderDTO.getDateTime());
        pulledOrder.setStatus(orderDTO.getStatus());
        pulledOrder.setCustomer(customer);
        pulledOrder.setOrderLines(orderLines);
        orderRepository.save(pulledOrder);
        return id;
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
