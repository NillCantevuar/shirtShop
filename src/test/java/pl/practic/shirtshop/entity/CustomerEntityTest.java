package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Contacts;
import pl.practic.shirtshop.entities.Customer;

import pl.practic.shirtshop.repositories.CustomerRepository;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerEntityTest {


    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void shouldAddAndGetOneCustomer_FirstNameLastNameCheck() {
        //given
        Customer customer = new Customer();

        String firstName = "Harry";
        String lastName = "Kane";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional =  customerRepository.findById(customer.getId());

        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(),firstName);
        Assert.assertEquals(customer2.getLastName(),lastName);

    }

    @Test
    public void shouldAddAndGetOneCustomerWithAdress_ConnectionTest_FirstNameLastNameCheck() {
        //given
        Customer customer = new Customer();
        Adress adress = new Adress();


        String firstName = "Harry";
        String lastName = "Kane";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAdress(adress);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional =  customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(),firstName);
        Assert.assertEquals(customer2.getLastName(),lastName);

    }

    @Test
    public void shouldAddAndGetOneCustomerWithContacts_ConnectionTest_FirstNameLastNameCheck() {
        //given
        Customer customer = new Customer();
        Contacts contacts = new Contacts();
        String firstName = "Harry";
        String lastName = "Kane";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContacts(contacts);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional =  customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(),firstName);
        Assert.assertEquals(customer2.getLastName(),lastName);

    }

    @Test
    public void shouldAddAndGetOneCustomerWithContactsAndAdress_ConnectionTest_FirstNameLastNameCheck() {
        //given
        Customer customer = new Customer();
        Contacts contacts = new Contacts();
        Adress adress = new Adress();
        String firstName = "Harry";
        String lastName = "Kane";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContacts(contacts);
        customer.setAdress(adress);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional =  customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(),firstName);
        Assert.assertEquals(customer2.getLastName(),lastName);

    }
    @Test
    public void shouldAddAndGetOneCustomerWithContactsAndAdress_CompatibilityTest() {
        //given
        Customer customer = new Customer();
        Contacts contacts = new Contacts();
        Adress adress = new Adress();
        String firstName = "Harry";
        String lastName = "Kane";
        String phoneNumber = "123456789";
        String street ="Moidowa";
        contacts.setPhoneNumber1(phoneNumber);
        adress.setStreet(street);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContacts(contacts);
        customer.setAdress(adress);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional =  customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(),firstName);
        Assert.assertEquals(customer2.getLastName(),lastName);
        Assert.assertEquals(phoneNumber,customer2.getContacts().getPhoneNumber1());
        Assert.assertEquals(street,customer2.getAdress().getStreet());

    }
}
