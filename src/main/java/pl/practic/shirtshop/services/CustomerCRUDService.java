package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.CustomerRepository;
@Service
public class CustomerCRUDService implements CRUDService<CustomerDTO> {

    @Autowired
    CustomerRepository customerRepository;

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
    public int update(CustomerDTO customerDTO, int id) {
        Customer pulledCustomer = customerRepository.getOne(id);
        pulledCustomer.setFirstName(customerDTO.getFirstName());
        pulledCustomer.setLastName(customerDTO.getLastName());
//        pulledCustomer.setAdress(customerDTO.getAdressId());
//        pulledCustomer.setContact(customerDTO.getContactId());
//        pulledCustomer.setOrder(customerDTO.getOrderId());
        //todo

        //todo
        return 0;
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }
}
