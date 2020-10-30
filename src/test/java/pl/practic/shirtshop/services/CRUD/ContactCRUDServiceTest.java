package pl.practic.shirtshop.services.CRUD;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.services.ContactCRUDService;
import pl.practic.shirtshop.support.ContactAbility;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactCRUDServiceTest {

//    @Autowired
//    ContactAbility contactAbility;
//
//    @Autowired
//    ContactCRUDService contactCRUDService;
//
//    @Transactional
//    @Test
//    public void shouldSaveAndFindContactUsignId_CompareFields_CR(){
//        //given
//        Contact contact = contactAbility.generateOneContact();
//        //when
//        contactCRUDService.save(contact);
//        Contact pulledContact = contactCRUDService.find(contact.getId());
//        //then
//        Assert.assertEquals(contact.getPhoneNumber1(),pulledContact.getPhoneNumber1());
//        Assert.assertEquals(contact.getEmail(),pulledContact.getEmail());
//    }
//
//    @Transactional
//    @Test
//    public void shouldThrowExceptionWhenSavingNull_C(){
//        //given
//        Contact contact = null;
//        //when
//        //then
//        Assert.assertThrows(InvalidDataAccessApiUsageException.class,()-> contactCRUDService.save(contact));
//    }


















    //find not existing throw
//    @Test
//    @Transactional
//    public void shouldThrowExceptionWhenGetingNotExisting(){
//        //given
//        //when
//        //then
//       Assert.assertThrows(EntityNotFoundException.class,() -> contactCRUDService.find(999999999));
//    }

}
