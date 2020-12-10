package pl.practic.shirtshop.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.entities.Customer;

@Component
public class CustomerAbility {

    @Autowired
    AdressAbility adressAbility;
    @Autowired
    ContactAbility contactAbility;


    public Customer generateOneCustomer(){

        Customer customer = new Customer();
        customer.setFirstName("Jakub");
        customer.setLastName("Moder");
        customer.setContact(null);
        customer.setAdress(null);
        return customer;

    }
    public CustomerDTO generateOneCustomerDTO(){

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Jakub");
        customer.setLastName("Moder");
        customer.setContactId(null);
        customer.setAdressId(null);
        return customer;

    }
    public Customer generateSecondCustomer(){

        Customer customer = new Customer();
        customer.setFirstName("Pierre-Emile");
        customer.setLastName("Hojbjerg");
        customer.setContact(null);
        customer.setAdress(null);
        return customer;

    }
    public CustomerDTO generateSecondCustomerDTO(){

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Pierre-Emile");
        customer.setLastName("Hojbjerg");
        customer.setContactId(null);
        customer.setAdressId(null);
        return customer;

    }
    public Customer generateOneCustomerWithContactAndAdress(){

        Customer customer = new Customer();
        customer.setFirstName("Jakub");
        customer.setLastName("Moder");
        customer.setContact(contactAbility.generateOneContact());
        customer.setAdress(adressAbility.generateOneAdress());
        return customer;

    }
    public CustomerDTO generateOneCustomerDTOWithContactAndAdress(){

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Jakub");
        customer.setLastName("Moder");
        customer.setContactId(contactAbility.generateOneContact().getId());
        customer.setAdressId(adressAbility.generateOneAdress().getId());
        return customer;

    }
    public Customer generateSecondCustomerWithContactAndAdress(){

        Customer customer = new Customer();
        customer.setFirstName("Pierre-Emile");
        customer.setLastName("Hojbjerg");
        customer.setContact(contactAbility.generateOneContact());
        customer.setAdress(adressAbility.generateOneAdress());
        return customer;

    }
    public CustomerDTO generateSecondCustomerDTOWithContactAndAdress(){

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Pierre-Emile");
        customer.setLastName("Hojbjerg");
        customer.setContactId(contactAbility.generateOneContact().getId());
        customer.setAdressId(adressAbility.generateOneAdress().getId());
        return customer;

    }
}
