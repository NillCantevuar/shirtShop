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
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.services.AdressCRUDService;
import pl.practic.shirtshop.services.CustomerCRUDService;
import pl.practic.shirtshop.support.AdressAbility;
import pl.practic.shirtshop.support.CustomerAbility;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdressCRUDServiceTest {


    @Autowired
    AdressCRUDService adressCRUDService;
    @Autowired
    CustomerCRUDService customerCRUDService;
    @Autowired
    AdressAbility adressAbility;
    @Autowired
    CustomerAbility customerAbility;
    @Autowired
    DTOMapper dtoMapper;


    @Transactional
    @Test
    public void shouldThrowExceptionWhenSavingNull_C() {
        //given
        AdressDTO adress = null;
        //when
        //then
        Assert.assertThrows(NullPointerException.class, () -> adressCRUDService.save(adress));
    }

    @Test
    @Transactional
    public void shouldSaveAndFindAdressUsignId_CompareFields_CR() {
        //given
        AdressDTO adress = adressAbility.generateOneAdressDTO();
        //when
        Integer IdOfSaved = adressCRUDService.save(adress);
        AdressDTO pulledAdress = adressCRUDService.find(IdOfSaved);

        //then
        Assert.assertEquals(adress.getCity(), pulledAdress.getCity());
        Assert.assertEquals(adress.getStreet(), pulledAdress.getStreet());
        Assert.assertEquals(adress.getCustomerId(), pulledAdress.getCustomerId());
    }

    @Test
    @Transactional
    public void shouldSaveAndFindAdressUsignIdWithCustomer_CompareFields_CR() {
        //given
        CustomerDTO customerDTO = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customerDTO);

        AdressDTO adress = adressAbility.generateOneAdressDTO();
        adress.setCustomerId(savedCustomerId);
        //when
        Integer IdOfSavedAdress = adressCRUDService.save(adress);
        AdressDTO pulledAdress = adressCRUDService.find(IdOfSavedAdress);
        //then
        Assert.assertEquals(adress.getCity(), pulledAdress.getCity());
        Assert.assertEquals(adress.getStreet(), pulledAdress.getStreet());
        Assert.assertEquals(adress.getCustomerId(), pulledAdress.getCustomerId());
    }


    @Test
    @Transactional
    public void shouldThrowException_TryUpdateNotExistingId_U() {
        //given
        AdressDTO adress = adressAbility.generateOneAdressDTO();
        //when
        //then
        Assert.assertThrows(EntityNotFoundException.class, () -> adressCRUDService.update(adress, 99999999));
    }

    @Test
    @Transactional
    public void shouldUpdateAdress_CompareFields_U() {
        //given
        AdressDTO adress = adressAbility.generateOneAdressDTO();
        AdressDTO updatingAdress = adressAbility.generateSecondAdressDTO();
        Integer savedId = adressCRUDService.save(adress);
        //when
        Integer updateId = adressCRUDService.update(updatingAdress,savedId);
        AdressDTO pulledAdress = adressCRUDService.find(updateId);
        //then
        Assert.assertEquals(updatingAdress.getFlatNumber(), pulledAdress.getFlatNumber());
    }
    @Test
    @Transactional
    public void shouldUpdateAdressWithCustomer_CompareFields_U() {
        //given
        CustomerDTO customer = customerAbility.generateOneCustomerDTO();
        Integer savedCustomerId = customerCRUDService.save(customer);

        AdressDTO adress = adressAbility.generateOneAdressDTO();
        adress.setCustomerId(savedCustomerId);
        AdressDTO updatingAdress = adressAbility.generateSecondAdressDTO();

        Integer savedId = adressCRUDService.save(adress);
        //when
        Integer updateId = adressCRUDService.update(updatingAdress,savedId);

        AdressDTO pulledAdress = adressCRUDService.find(updateId);
        //then
        Assert.assertEquals(updatingAdress.getFlatNumber(), pulledAdress.getFlatNumber());

        Assert.assertEquals(updatingAdress.getCustomerId(), pulledAdress.getCustomerId());
    }

    @Test
    @Transactional
    public void shouldThrowException_updatingByNull_U() {
        //given
        AdressDTO adress = adressAbility.generateOneAdressDTO();
        AdressDTO updatingAdress = null;
        adressCRUDService.save(adress);
        //when
        //then
        Assert.assertThrows(InvalidDataAccessApiUsageException.class, () -> adressCRUDService.update(updatingAdress, adress.getId()));


    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteAdressUsignId_isNull_D() {
        //given
        AdressDTO adress = adressAbility.generateOneAdressDTO();
        //when
        Integer savedId = adressCRUDService.save(adress);
        adressCRUDService.delete(savedId);
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class, () -> adressCRUDService.find(savedId));
    }


    @Test
    @Transactional
    public void shouldThrowException_TryDeleteNotExistingId_D() {
        //given
        //when
        //then
        Assert.assertThrows(EmptyResultDataAccessException.class, () -> adressCRUDService.delete(99999999));
    }


}
