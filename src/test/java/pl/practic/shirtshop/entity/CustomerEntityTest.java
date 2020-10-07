package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.entities.Customer;

import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerEntityTest {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

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
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());

        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(), firstName);
        Assert.assertEquals(customer2.getLastName(), lastName);

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
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(), firstName);
        Assert.assertEquals(customer2.getLastName(), lastName);

    }

    @Test
    public void shouldAddAndGetOneCustomerWithContact_ConnectionTest_FirstNameLastNameCheck() {
        //given
        Customer customer = new Customer();
        Contact contact = new Contact();
        String firstName = "Harry";
        String lastName = "Kane";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContact(contact);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(), firstName);
        Assert.assertEquals(customer2.getLastName(), lastName);

    }

    @Test
    public void shouldAddAndGetOneCustomerWithContactAndAdress_ConnectionTest_FirstNameLastNameCheck() {
        //given
        Customer customer = new Customer();
        Contact contact = new Contact();
        Adress adress = new Adress();
        String firstName = "Harry";
        String lastName = "Kane";
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContact(contact);
        customer.setAdress(adress);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(), firstName);
        Assert.assertEquals(customer2.getLastName(), lastName);

    }

    @Test
    public void shouldAddAndGetOneCustomerWithContactAndAdress_CompatibilityTest() {
        //given
        Customer customer = new Customer();
        Contact contact = new Contact();
        Adress adress = new Adress();
        String firstName = "Harry";
        String lastName = "Kane";
        String phoneNumber = "123456789";
        String street = "Moidowa";
        contact.setPhoneNumber1(phoneNumber);
        adress.setStreet(street);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContact(contact);
        customer.setAdress(adress);
        //when
        customerRepository.save(customer);
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        Customer customer2 = customerOptional.get();
        //then
        Assert.assertEquals(customer2.getFirstName(), firstName);
        Assert.assertEquals(customer2.getLastName(), lastName);
        Assert.assertEquals(phoneNumber, customer2.getContact().getPhoneNumber1());
        Assert.assertEquals(street, customer2.getAdress().getStreet());

    }

    @Test
    @Transactional
    public void CustomerOrderAdressContactIntegrationTest() {
        //given
        Order order = new Order();
        Contact contact = new Contact();
        Adress adress = new Adress();
        String status = "OK";
        String firstName = "Harry";
        String lastName = "Kane";
        String phoneNumber = "123456789";
        String street = "Moidowa";
        contact.setPhoneNumber1(phoneNumber);
        adress.setStreet(street);
        order.setStatus(status);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAdress(adress);
        customer.setContact(contact);
        order.setCustomer(customer);
        //when
        orderRepository.save(order);
        Order pulledOrder = orderRepository.getOne(order.getId());
        //then
        Assert.assertEquals(firstName, pulledOrder.getCustomer().getFirstName());
        Assert.assertEquals(lastName, pulledOrder.getCustomer().getLastName());
        Assert.assertEquals(phoneNumber,pulledOrder.getCustomer().getContact().getPhoneNumber1());
        Assert.assertEquals(street,pulledOrder.getCustomer().getAdress().getStreet());
        Assert.assertEquals(status,pulledOrder.getStatus());
    }
}
