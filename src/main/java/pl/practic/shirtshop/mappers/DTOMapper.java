package pl.practic.shirtshop.mappers;

import org.aspectj.weaver.ast.Or;
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

        if(dto.getCustomerId() != null) {
            return new Contact(
                    dto.getPhoneNumber1(),
                    dto.getPhoneNumber2(),
                    dto.getEmail(),
                    dto.getWww(),
                    dto.getFax(),
                    customerRepository.getOne(dto.getCustomerId()));
        }
        return new Contact(
                dto.getPhoneNumber1(),
                dto.getPhoneNumber2(),
                dto.getEmail(),
                dto.getWww(),
                dto.getFax(),
                null);

    }
    public  Customer fromCustomerDTO(CustomerDTO dto) {

            Adress adress = null;
            Contact contact = null;
            List<Order> orders = null;

            if (dto.getAdressId() != null){
                adress = adressRepository.getOne(dto.getAdressId());
            }
            if (dto.getContactId() != null){
                contact = contactRepository.getOne(dto.getContactId());
            }
            if (dto.getOrdersId() != null ){
                if (!dto.getOrdersId().isEmpty()) {
                    orders = fillOrdersList(dto.getOrdersId());
                }
            }

        return new Customer(
                dto.getFirstName(),
                dto.getLastName(),
                adress,
                contact,
                orders);
    }


    public  Order fromOrderDTO(OrderDTO dto) {
        return new Order(dto.getId(),
                customerRepository.getOne(dto.getCustomerId()),
                dto.getDateTime(),
                dto.getStatus(),
                fillOrderLinesList(dto.getOrderLinesId()));

    }

    public  OrderLine fromOrderLineDTO(OrderLineDTO dto) {

        Product product =null;
        Order order = null;

        if (dto.getProductId() != null){
            product =  productRepository.getOne(dto.getProductId());
        }
        if(dto.getOrderId()!=null){
           order = orderRepository.getOne(dto.getOrderId());
        }
        return new OrderLine(
                product,
                order,
                dto.getQuantity());

    }
    public  Product fromProductDTO(ProductDTO dto) {

        if (dto.getOrderLinesId() != null){
            if (!dto.getOrderLinesId().isEmpty()){
                return new Product(
                        ProductType.valueOf(dto.getType()),
                        dto.getBrand(),
                        dto.getPrice(),
                        dto.getName(),
                        dto.getStock(),
                        fillOrderLinesList(dto.getOrderLinesId()));
            }
        }
        return new Product(
                ProductType.valueOf(dto.getType()),
                dto.getBrand(),
                dto.getPrice(),
                dto.getName(),
                dto.getStock(),
               null);
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
