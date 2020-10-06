package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Contacts;
import pl.practic.shirtshop.repositories.ContactsRepository;

import javax.transaction.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactsEntityTest {


    @Autowired
    ContactsRepository contactsRepository;

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


}
