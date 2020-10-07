package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Contacts;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.repositories.ContactsRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactsEntityTest {


    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional
    public void shouldAddAndGetOneContactFromDb_PhoneNumberWWWCheck() {
        //given
        String www = "www.to.com";
        String phoneNumber1 = "123456789";
        Contacts contacts = new Contacts();
        contacts.setWww(www);
        contacts.setPhoneNumber1(phoneNumber1);
        //when
        contactsRepository.save(contacts);
        Contacts contactsPulled = contactsRepository.getOne(contacts.getId());
        //then
        Assert.assertEquals(www, contactsPulled.getWww());
        Assert.assertEquals(phoneNumber1, contactsPulled.getPhoneNumber1());
    }

    @Test
    @Transactional
    public void shouldAddContactToCustomerAndGetDirectlyFromDB_PhoneNumber2EmailCheck(){
        //given
        String phoneNumber ="123456789";
        String email ="auuu@ww.pl";
        Contacts contacts = new Contacts();
        contacts.setEmail(email);
        contacts.setPhoneNumber2(phoneNumber);

        Customer customer = new Customer();
        customer.setContacts(contacts);
        //when
        customerRepository.save(customer);
        Contacts contacts2 = contactsRepository.getOne(contacts.getId());
        //then
        Assert.assertEquals(phoneNumber,contacts2.getPhoneNumber2());
        Assert.assertEquals(email,contacts2.getEmail());

    }

    @Test
    @Transactional
    public void shouldAddAndGetContactFromCustomer_FaxCheck(){
        //given
        String fax ="123456789";
        Contacts contacts = new Contacts();
        contacts.setFax(fax);

        Customer customer = new Customer();
        customer.setContacts(contacts);
        //when
        customerRepository.save(customer);
        Customer customer2 = customerRepository.getOne(customer.getId());
        //then
        Assert.assertEquals(fax,customer2.getContacts().getFax());


    }

}
