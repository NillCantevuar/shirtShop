package pl.practic.shirtshop.services.CRUD;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.services.AdressCRUDService;
import pl.practic.shirtshop.services.ContactCRUDService;
import pl.practic.shirtshop.services.CustomerCRUDService;
import pl.practic.shirtshop.support.AdressAbility;
import pl.practic.shirtshop.support.ContactAbility;
import pl.practic.shirtshop.support.CustomerAbility;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactCRUDServiceTest {

    @Autowired
    ContactAbility contactAbility;

    @Autowired
    ContactCRUDService contactCRUDService;

    @Autowired
    CustomerCRUDService customerCRUDService;

    @Autowired
    CustomerAbility customerAbility;

    @Autowired
    DTOMapper dtoMapper;
    @Transactional
    @Test
    public void shouldThrowExceptionWhenSavingNull_C() {
        //given
        ContactDTO contactDTO = null;
        //when
        //then
        Assert.assertThrows(NullPointerException.class, () -> contactCRUDService.save(contactDTO));
    }

    @Test
    @Transactional
    public void shouldSaveAndFindContactUsignId_CompareFields_CR() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        //when
        Integer IdOfSaved = contactCRUDService.save(contactDTO);
        ContactDTO pulledContact = contactCRUDService.find(IdOfSaved);

        //then
        Assert.assertEquals(contactDTO.getEmail(), pulledContact.getEmail());
        Assert.assertEquals(contactDTO.getPhoneNumber1(), pulledContact.getPhoneNumber1());
        Assert.assertEquals(contactDTO.getCustomerId(), pulledContact.getCustomerId());
    }

    @Test
    @Transactional
    public void shouldSaveAndFindAdressUsignIdWithCustomer_CompareFields_CR() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customerDTO);

        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        contactDTO.setCustomerId(savedCustomerId);
        //when
        Integer idOfSavedContact = customerCRUDService.save(customerDTO);
        ContactDTO pulledContact = contactCRUDService.find(idOfSavedContact);
        //then
        Assert.assertEquals(contactDTO.getEmail(), pulledContact.getEmail());
        Assert.assertEquals(contactDTO.getPhoneNumber1(), pulledContact.getPhoneNumber1());
        Assert.assertEquals(contactDTO.getCustomerId(), pulledContact.getCustomerId());
    }


    @Test
    @Transactional
    public void shouldThrowException_TryUpdateNotExistingId_U() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        //when
        //then
        Assert.assertThrows(EntityNotFoundException.class, () -> contactCRUDService.update(contactDTO, 99999999));
    }

    @Test
    @Transactional
    public void shouldUpdateContact_CompareFields_U() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        ContactDTO updatingContact = contactAbility.generateSecondContactDTO();
        Integer savedId = contactCRUDService.save(contactDTO);
        //when
        Integer updatedId = contactCRUDService.update(updatingContact,savedId);
        ContactDTO pulledContact = contactCRUDService.find(savedId);
        //then
        Assert.assertEquals(updatingContact.getWww(), pulledContact.getWww());
    }
    @Test
    @Transactional
    public void shouldUpdateAdressWithCustomer_CompareFields_U() {
        //given
        CustomerDTO customer = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customer);

        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        contactDTO.setCustomerId(savedCustomerId);
        ContactDTO updatingContact = contactAbility.generateSecondContactDTO();

        Integer savedId = contactCRUDService.save(contactDTO);
        //when
        Integer updateId = contactCRUDService.update(updatingContact,savedId);

        ContactDTO pulledContact = contactCRUDService.find(savedCustomerId);
        //then
        Assert.assertEquals(updatingContact.getWww(), pulledContact.getWww());

        Assert.assertEquals(updatingContact.getCustomerId(), pulledContact.getCustomerId());
    }

    @Test
    @Transactional
    public void shouldThrowException_updatingByNull_U() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        ContactDTO updatingContact = null;
        contactCRUDService.save(contactDTO);
        //when
        //then
        Assert.assertThrows(InvalidDataAccessApiUsageException.class, () -> contactCRUDService.update(updatingContact, contactDTO.getId()));


    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteAdressUsignId_isNull_D() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        //when
        Integer savedId = contactCRUDService.save(contactDTO);
        contactCRUDService.delete(savedId);
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class, () -> contactCRUDService.find(savedId));
    }


    @Test
    @Transactional
    public void shouldThrowException_TryDeleteNotExistingId_D() {
        //given
        //when
        //then
        Assert.assertThrows(EmptyResultDataAccessException.class, () -> contactCRUDService.delete(99999999));
    }


}





