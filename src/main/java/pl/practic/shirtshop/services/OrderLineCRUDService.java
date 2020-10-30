package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.OrderLineDTO;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.OrderLineRepository;

@Service
public class OrderLineCRUDService implements CRUDService<OrderLineDTO> {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Override
    public OrderLineDTO find(int id) {
        return orderLineRepository.getOne(id).toDTO();
    }

    @Override
    public int save(OrderLineDTO orderLineDTO) {
        OrderLine saved = orderLineRepository.save(OrderLine.fromDTO(orderLineDTO));
        return saved.getId();
    }

    @Override
    public OrderLineDTO update(OrderLineDTO orderLineDTO, int id) {
        OrderLine pulledOrderLine = orderLineRepository.getOne(id);
        pulledOrderLine.setQuantity(orderLineDTO.getQuantity());
        pulledOrderLine.setOrder(null); //todo
        pulledOrderLine.setProduct(null); //todo
        OrderLine saved = orderLineRepository.save(pulledOrderLine);
        return saved.toDTO();
    }

    @Override
    public void delete(int id) {
        orderLineRepository.deleteById(id);
    }
}
