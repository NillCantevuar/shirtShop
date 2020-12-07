package pl.practic.shirtshop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.practic.shirtshop.dto.*;
import pl.practic.shirtshop.entities.*;
import pl.practic.shirtshop.enums.ProductType;
import pl.practic.shirtshop.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOMapper {

    @Autowired
    AdressRepository adressRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    ProductRepository productRepository;

    public Adress fromAdressDTO(AdressDTO dto) {
        //if customer exist
        if (dto.getCustomerId() != null) {
            return new Adress(
                    dto.getStreet(),
                    dto.getHouseNumber(),
                    dto.getFlatNumber(),
                    dto.getCity(),
                    dto.getState(),
                    dto.getPostalCode(),
                    customerRepository.getOne(dto.getCustomerId()));
        }
        return new Adress(
                dto.getStreet(),
                dto.getHouseNumber(),
                dto.getFlatNumber(),
                dto.getCity(),
                dto.getState(),
                dto.getPostalCode(),
                null);
    }

    public  Contact fromContactDTO(ContactDTO dto) {
        return new Contact(dto.getId(),
                dto.getPhoneNumber1(),
                dto.getPhoneNumber2(),
                dto.getEmail(),
                dto.getWww(),
                dto.getFax(),
                customerRepository.getOne(dto.getCustomerId()));
    }
    public  Customer fromCustomerDTO(CustomerDTO dto) {
        return new Customer(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                adressRepository.getOne(dto.getAdressId()),
                contactRepository.getOne(dto.getContactId()),
                fillOrdersList(dto.getOrderId()));
    }
    public  Order fromOrderDTO(OrderDTO dto) {
        return new Order(dto.getId(),
                customerRepository.getOne(dto.getCustomerId()),
                dto.getDateTime(),
                dto.getStatus(),
                fillOrderLinesList(dto.getOrderLinesId()));

    }

    public  OrderLine fromOrderLineDTO(OrderLineDTO dto) {
        return new OrderLine(dto.getId(),
                productRepository.getOne(dto.getProductId()),//TODO
                orderRepository.getOne(dto.getOrderId()),//TODO
                dto.getQuantity());//TODO

    }
    public  Product fromProductDTO(ProductDTO dto) {
        return new Product(dto.getId(),
                ProductType.valueOf(dto.getType()),
                dto.getBrand(),
                dto.getPrice(),
                dto.getName(),
                dto.getStock(),
                fillOrderLinesList(dto.getOrderLineId())); //TODO
    }

    private List<Order> fillOrdersList(List<Integer> indexes) {
        List<Order> orders = new ArrayList<>();

        for (Integer i : indexes
        ) {
            orders.add(orderRepository.getOne(i));

        }
        return orders;
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
