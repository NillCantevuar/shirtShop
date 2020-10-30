package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactEntityTest {


    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CustomerRepository customerRepository;

//    @Test
//    @Transactional
//    public void shouldAddAndGetOneContactFromDb_PhoneNumberWWWCheck() {
//        //given
//        String www = "www.to.com";
//        String phoneNumber1 = "123456789";
//
//        Contact contact = new Contact();
//        contact.setWww(www);
//        contact.setPhoneNumber1(phoneNumber1);
//        //when
//        contactRepository.save(contact);
//        Contact contactPulled = contactRepository.getOne(contact.getId());
//        //then
//        Assert.assertEquals(www, contactPulled.getWww());
//        Assert.assertEquals(phoneNumber1, contactPulled.getPhoneNumber1());
//    }
//
//    @Test
//    @Transactional
//    public void shouldAddContactToCustomerAndGetDirectlyFromDB_PhoneNumber2EmailCheck(){
//        //given
//        String phoneNumber ="123456789";
//        String email ="auuu@ww.pl";
//        Contact contact = new Contact();
//        contact.setEmail(email);
//        contact.setPhoneNumber2(phoneNumber);
//
//        Customer customer = new Customer();
//        customer.setContact(contact);
//        //when
//        customerRepository.save(customer);
//        Contact contact2 = contactRepository.getOne(contact.getId());
//        //then
//        Assert.assertEquals(phoneNumber, contact2.getPhoneNumber2());
//        Assert.assertEquals(email, contact2.getEmail());
//
//    }
//
//    @Test
//    @Transactional
//    public void shouldAddAndGetContactFromCustomer_FaxCheck(){
//        //given
//        String fax ="123456789";
//        Contact contact = new Contact();
//        contact.setFax(fax);
//
//        Customer customer = new Customer();
//        customer.setContact(contact);
//        //when
//        customerRepository.save(customer);
//        Customer customer2 = customerRepository.getOne(customer.getId());
//        //then
//        Assert.assertEquals(fax,customer2.getContact().getFax());
//
//
//    }

}
