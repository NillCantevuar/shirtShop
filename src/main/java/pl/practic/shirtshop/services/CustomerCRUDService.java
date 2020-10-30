package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public CustomerDTO find(int id) {
        return customerRepository.getOne(id).toDTO();
    }

    @Override
    public int save(CustomerDTO customerDTO) {
        Customer saved = customerRepository.save(Customer.fromDTO(customerDTO));
        return saved.getId();

    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, int id) {
        Customer pulledCustomer = customerRepository.getOne(id);
        pulledCustomer.setFirstName(customerDTO.getFirstName());
        pulledCustomer.setLastName(customerDTO.getLastName());
        pulledCustomer.setAdress(adressRepository.getOne(customerDTO.getAdressId()));
        pulledCustomer.setContact(contactRepository.getOne(customerDTO.getContactId()));
        pulledCustomer.setOrders(fillOrdersList(customerDTO.getOrderId()));
        Customer saved = customerRepository.save(pulledCustomer);
        return saved.toDTO();
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    private List<Order> fillOrdersList(List<Integer> indexes) {
        List<Order> orders = new ArrayList<>();

        for (Integer i : indexes
        ) {
            orders.add(orderRepository.getOne(i));

        }
        return orders;
    }
}
