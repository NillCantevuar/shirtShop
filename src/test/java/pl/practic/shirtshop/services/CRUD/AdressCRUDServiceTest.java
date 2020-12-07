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
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.services.AdressCRUDService;
import pl.practic.shirtshop.support.AdressAbility;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdressCRUDServiceTest {


    @Autowired
    AdressCRUDService adressCRUDService;
    @Autowired
    AdressAbility adressAbility;
    @Autowired
    DTOMapper dtoMapper;

//    @Transactional
//    @Test
//    public void shouldThrowExceptionWhenSavingNull_C() {
//        //given
//        Adress adress = null;
//        //when
//        //then
//        Assert.assertThrows(InvalidDataAccessApiUsageException.class, () -> adressCRUDService.save(adress));
//    }
//
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
        Assert.assertEquals(adress.getCustomerId(),pulledAdress.getCustomerId());
    }
//
//    @Test
//    @Transactional
//    public void shouldThrowException_TryUpdateNotExistingId_U() {
//        //given
//        Adress adress = adressAbility.generateOneAdress();
//        //when
//        //then
//        Assert.assertThrows(EntityNotFoundException.class, () -> adressCRUDService.update(adress, 99999999));
//    }
//
//    @Test
//    @Transactional
//    public void shouldUpdateAdress_CompareFields_U() {
//        //given
//        Adress adress = adressAbility.generateOneAdress();
//        Adress updatingAdress = adressAbility.generateSecondAdress();
//        adressCRUDService.save(adress);
//        //when
//        adressCRUDService.update(updatingAdress, adress.getId());
//        Adress pulledAdress = adressCRUDService.find(adress.getId());
//        //then
//        Assert.assertEquals(updatingAdress.getFlatNumber(), pulledAdress.getFlatNumber());
//    }
//
//    @Test
//    @Transactional
//    public void shouldThrowException_updatingByNull_U() {
//        //given
//        Adress adress = adressAbility.generateOneAdress();
//        Adress updatingAdress = null;
//        adressCRUDService.save(adress);
//        //when
//        //then
//        Assert.assertThrows(NullPointerException.class, () -> adressCRUDService.update(updatingAdress, adress.getId()));
//
//
//    }
//
//    @Test
//    @Transactional
//    public void shouldSaveAndDeleteAdressUsignId_isNull_D() {
//        //given
//        Adress adress = adressAbility.generateOneAdress();
//        //when
//        adressCRUDService.save(adress);
//        adressCRUDService.delete(adress.getId());
//        //then
//        Assert.assertThrows(JpaObjectRetrievalFailureException.class, () -> adressCRUDService.find(adress.getId()));
//    }
//
//
//    @Test
//    @Transactional
//    public void shouldThrowException_TryDeleteNotExistingId_D() {
//        //given
//        //when
//        //then
//        Assert.assertThrows(EmptyResultDataAccessException.class, () -> adressCRUDService.delete(99999999));
//    }


}
