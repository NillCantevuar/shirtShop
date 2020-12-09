package pl.practic.shirtshop.services.CRUD;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderRepository;
import pl.practic.shirtshop.services.AdressCRUDService;
import pl.practic.shirtshop.services.ContactCRUDService;
import pl.practic.shirtshop.services.CustomerCRUDService;
import pl.practic.shirtshop.services.OrderCRUDService;
import pl.practic.shirtshop.support.AdressAbility;
import pl.practic.shirtshop.support.ContactAbility;
import pl.practic.shirtshop.support.CustomerAbility;
import pl.practic.shirtshop.support.OrderAbility;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerCRUDServiceTest {

    @Autowired
    AdressCRUDService adressCRUDService;
    @Autowired
    CustomerCRUDService customerCRUDService;
    @Autowired
    ContactCRUDService contactCRUDService;
    @Autowired
    OrderCRUDService orderCRUDService;
    @Autowired
    CustomerAbility customerAbility;
    @Autowired
    AdressAbility adressAbility;
    @Autowired
    ContactAbility contactAbility;
    @Autowired
    OrderAbility orderAbility;
    @Autowired
    DTOMapper dtoMapper;

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerAloneUsignId_CompareFields_CR() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        //when
        Integer savedId = customerCRUDService.save(customerDTO);
        CustomerDTO pulledCustomer = customerCRUDService.find(savedId);
        //then
        Assert.assertEquals(customerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(customerDTO.getLastName(),pulledCustomer.getLastName());

    }

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerWithAdressUsignId_CompareFields_CR() {
        //given
        AdressDTO adressDTO = adressAbility.generateOneAdressDTO();
        Integer savedAdressId = adressCRUDService.save(adressDTO);
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setAdressId(savedAdressId);
        //when
        Integer savedId = customerCRUDService.save(customerDTO);
        CustomerDTO pulledCustomer = customerCRUDService.find(savedId);
        //then
        Assert.assertEquals(customerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(customerDTO.getLastName(),pulledCustomer.getLastName());
        Assert.assertEquals( adressDTO.getCity() ,adressCRUDService.find(pulledCustomer.getAdressId()).getCity());
    }

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerWithContactUsignId_CompareFields_CR() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        Integer savedContactId = contactCRUDService.save(contactDTO);
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setContactId(savedContactId);
        //when
        Integer savedId = customerCRUDService.save(customerDTO);
        CustomerDTO pulledCustomer = customerCRUDService.find(savedId);
        //then
        Assert.assertEquals(customerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(customerDTO.getLastName(),pulledCustomer.getLastName());
        Assert.assertEquals( contactDTO.getPhoneNumber1() ,contactCRUDService.find(pulledCustomer.getContactId()).getPhoneNumber1());
    }

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerWithContactAndAdressUsignId_CompareFields_CR() {
        //given
        AdressDTO adressDTO = adressAbility.generateOneAdressDTO();
        Integer savedAdressId = adressCRUDService.save(adressDTO);
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        Integer savedContactId = contactCRUDService.save(contactDTO);
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setAdressId(savedAdressId);
        customerDTO.setContactId(savedContactId);
        //when
        Integer savedId = customerCRUDService.save(customerDTO);
        CustomerDTO pulledCustomer = customerCRUDService.find(savedId);
        //then
        Assert.assertEquals(customerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(customerDTO.getLastName(),pulledCustomer.getLastName());
        Assert.assertEquals( contactDTO.getPhoneNumber1() ,contactCRUDService.find(pulledCustomer.getContactId()).getPhoneNumber1());
        Assert.assertEquals( adressDTO.getCity() ,adressCRUDService.find(pulledCustomer.getAdressId()).getCity());

    }

    @Test
    @Transactional
    public void shouldUpdateCustomer_CompareFields_U() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        CustomerDTO updatingCustomerDTO = customerAbility.generateSecondCustomerDTO();
        Integer savedCustomerDTOId = customerCRUDService.save(customerDTO);
        //when
        customerCRUDService.update(updatingCustomerDTO,savedCustomerDTOId);
        CustomerDTO pulledCustomer = customerCRUDService.find(savedCustomerDTOId);
        //then
        Assert.assertEquals(updatingCustomerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(updatingCustomerDTO.getLastName(),pulledCustomer.getLastName());

    }
    @Test
    @Transactional
    public void shouldUpdateCustomerWithContact_CompareFields_U() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        Integer contactDTOId = contactCRUDService.save(contactDTO);
        ContactDTO updatingContactDTO = contactAbility.generateSecondContactDTO();
        Integer updatingContactDTOId = contactCRUDService.save(updatingContactDTO);

        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setContactId(contactDTOId);
        Integer savedCustomerDTOId = customerCRUDService.save(customerDTO);

        CustomerDTO updatingCustomerDTO = customerAbility.generateSecondCustomerDTO();
        updatingCustomerDTO.setContactId(updatingContactDTOId);

        //when
        customerCRUDService.update(updatingCustomerDTO,savedCustomerDTOId);
        CustomerDTO pulledCustomer = customerCRUDService.find(savedCustomerDTOId);
        //then
        Assert.assertEquals(updatingCustomerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(updatingCustomerDTO.getLastName(),pulledCustomer.getLastName());
        Assert.assertEquals( updatingContactDTO.getPhoneNumber1() ,contactCRUDService.find(pulledCustomer.getContactId()).getPhoneNumber1());
    }
    @Test
    @Transactional
    public void shouldUpdateCustomerWithAdress_CompareFields_U() {
        //given
        AdressDTO adressDTO = adressAbility.generateOneAdressDTO();
        Integer adressDTOId = adressCRUDService.save(adressDTO);
        AdressDTO updatingAdressDTO = adressAbility.generateSecondAdressDTO();
        Integer updatingAdressDTOId = adressCRUDService.save(updatingAdressDTO);

        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setAdressId(adressDTOId);
        Integer savedCustomerDTOId = customerCRUDService.save(customerDTO);

        CustomerDTO updatingCustomerDTO = customerAbility.generateSecondCustomerDTO();
        updatingCustomerDTO.setAdressId(updatingAdressDTOId);
        //when
        customerCRUDService.update(updatingCustomerDTO,savedCustomerDTOId);
        CustomerDTO pulledCustomer= customerCRUDService.find(savedCustomerDTOId);
        //then
        Assert.assertEquals(updatingCustomerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(updatingCustomerDTO.getLastName(),pulledCustomer.getLastName());
        Assert.assertEquals(updatingAdressDTO.getFlatNumber(),adressCRUDService.find(pulledCustomer.getAdressId()).getFlatNumber());

    }
    @Test
    @Transactional
    public void shouldUpdateCustomerWithContactAndAdress_CompareFields_U() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        Integer contactDTOId = contactCRUDService.save(contactDTO);
        ContactDTO updatingContactDTO = contactAbility.generateSecondContactDTO();
        Integer updatingContactDTOId = contactCRUDService.save(updatingContactDTO);

        AdressDTO adressDTO = adressAbility.generateOneAdressDTO();
        Integer adressDTOId = adressCRUDService.save(adressDTO);
        AdressDTO updatingAdressDTO = adressAbility.generateSecondAdressDTO();
        Integer updatingAdressDTOId = adressCRUDService.save(updatingAdressDTO);

        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setContactId(contactDTOId);
        customerDTO.setAdressId(adressDTOId);
        Integer savedCustomerDTOId = customerCRUDService.save(customerDTO);

        CustomerDTO updatingCustomerDTO = customerAbility.generateSecondCustomerDTO();
        updatingCustomerDTO.setAdressId(updatingAdressDTOId);
        updatingCustomerDTO.setContactId(updatingContactDTOId);
        //when
        customerCRUDService.update(updatingCustomerDTO,savedCustomerDTOId);
        CustomerDTO pulledCustomer= customerCRUDService.find(savedCustomerDTOId);
        //then
        Assert.assertEquals(updatingCustomerDTO.getFirstName(),pulledCustomer.getFirstName());
        Assert.assertEquals(updatingCustomerDTO.getLastName(),pulledCustomer.getLastName());
        Assert.assertEquals(updatingAdressDTO.getFlatNumber(),adressCRUDService.find(pulledCustomer.getAdressId()).getFlatNumber());
        Assert.assertEquals( updatingContactDTO.getPhoneNumber1() ,contactCRUDService.find(pulledCustomer.getContactId()).getPhoneNumber1());
        
    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteCustomerUsignId_isNull_D() {
        //given
        ContactDTO contactDTO = contactAbility.generateOneContactDTO();
        Integer contactDTOId = contactCRUDService.save(contactDTO);
        AdressDTO adressDTO = adressAbility.generateOneAdressDTO();
        Integer adressDTOId = adressCRUDService.save(adressDTO);
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        customerDTO.setContactId(contactDTOId);
        customerDTO.setAdressId(adressDTOId);
        Integer savedCustomerDTOId = customerCRUDService.save(customerDTO);
        //when
        customerCRUDService.delete(savedCustomerDTOId);
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class, () -> adressCRUDService.find(adressDTOId));
        Assert.assertThrows(JpaObjectRetrievalFailureException.class, () -> contactCRUDService.find(contactDTOId));
        Assert.assertThrows(JpaObjectRetrievalFailureException.class, ()-> customerCRUDService.find(savedCustomerDTOId));
        //Ze Customera nie ma w bazie
        //Ze Adresu nie ma w bazie
        //Ze Contact nie ma w bazie




    }


}
