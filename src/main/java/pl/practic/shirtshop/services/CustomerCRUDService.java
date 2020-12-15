package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.entities.*;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerCRUDService implements CRUDService<CustomerDTO> {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AdressRepository adressRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DTOMapper dtoMapper;


    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(Customer::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO find(Integer id) {
        return customerRepository.getOne(id).toDTO();
    }

    @Override
    public Integer save(CustomerDTO customerDTO) {
        Customer saved = customerRepository.save(dtoMapper.fromCustomerDTO(customerDTO));
        return saved.getId();

    }

    @Override
    public Integer update(CustomerDTO customerDTO, Integer id) {

        Adress adress = null;
        Contact contact =null;
        List<Order> orders = null;
        Customer pulledCustomer = customerRepository.getOne(id);
        if (customerDTO.getAdressId() != null){
            adress = adressRepository.getOne(customerDTO.getAdressId());
        }
        if (customerDTO.getContactId() != null){
            contact = contactRepository.getOne(customerDTO.getContactId());
        }
        if(customerDTO.getOrdersId() != null){
            if (!customerDTO.getOrdersId().isEmpty()){
               orders = fillOrdersList(customerDTO.getOrdersId());
            }
        }

        pulledCustomer.setFirstName(customerDTO.getFirstName());
        pulledCustomer.setLastName(customerDTO.getLastName());
        pulledCustomer.setAdress(adress);
        pulledCustomer.setContact(contact);
        pulledCustomer.setOrders(orders);
        customerRepository.save(pulledCustomer);
        return id;
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    private List<Order> fillOrdersList(List<Integer> indexes) {
        List<Order> orders = new ArrayList<>();
        for (Integer i : indexes)
        { orders.add(orderRepository.getOne(i)); }
        return orders;
    }
}
