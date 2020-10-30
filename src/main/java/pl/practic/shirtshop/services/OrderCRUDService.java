package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.OrderRepository;

@Service
public class OrderCRUDService implements CRUDService<OrderDTO> {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDTO find(int id) {
        return orderRepository.getOne(id).toDTO();

    }

    @Override
    public int save(OrderDTO orderDTO) {
        Order saved = orderRepository.save(Order.fromDTO(orderDTO));
        return saved.getId();
    }

    @Override
    public int update(OrderDTO orderDTO, int id) {
        final Order pulledOrder = orderRepository.getOne(id);
        pulledOrder.setDateTime(orderDTO.getDateTime());
        pulledOrder.setStatus(orderDTO.getStatus());
        pulledOrder.setCustomer(null); //todo
        pulledOrder.setOrderLines(null); //todo
        orderRepository.save(pulledOrder);

        return 0; //todo
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }
}
