package pl.practic.shirtshop.services.CRUD;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Adress;
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



    @Test
    @Transactional
    public void shouldSaveAndFindAdressUsignId_CompareFields(){
        //given
        Adress adress = adressAbility.generateOneAdress();
        //when
        adressCRUDService.save(adress);
        Adress pulledAdress = adressCRUDService.find(adress.getId());
        //then
        Assert.assertEquals(adress.getCity(),pulledAdress.getCity());
        Assert.assertEquals(adress.getStreet(),pulledAdress.getStreet());
    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteAdressUsignId_isNull(){
        //given
        Adress adress = adressAbility.generateOneAdress();
        //when
        adressCRUDService.save(adress);
        adressCRUDService.delete(adress.getId());
        //then
        Assert.assertThrows(JpaObjectRetrievalFailureException.class,()->adressCRUDService.find(adress.getId()));
    }

    @Test
    @Transactional
    public void shouldThrowException_TryUpdateNotExistingId(){
        //given
        Adress adress = adressAbility.generateOneAdress();
        //when
        //then
        Assert.assertThrows(EntityNotFoundException.class, () -> adressCRUDService.update(adress,99999999));
    }

    @Test
    @Transactional
    public void shouldThrowException_TryDeleteNotExistingId(){
        //given
        //when
        //then
        Assert.assertThrows(EmptyResultDataAccessException.class, () -> adressCRUDService.delete(99999999));
    }

    @Test
    @Transactional
    public void shouldUpdateAdress_CompareFields(){
        //given
        Adress adress = adressAbility.generateOneAdress();
        Adress updatingAdress = adressAbility.generateSecondAdress();
        adressCRUDService.save(adress);
        //when
        adressCRUDService.update(updatingAdress,adress.getId());
        Adress pulledAdress = adressCRUDService.find(adress.getId());
        //then
        Assert.assertEquals(updatingAdress.getFlatNumber(),pulledAdress.getFlatNumber());


    }
    @Test
    @Transactional
    public void shouldThrowException_updatingByNull(){
        //given
        Adress adress = adressAbility.generateOneAdress();
        Adress updatingAdress = null;
        adressCRUDService.save(adress);
        //when
        //then
       Assert.assertThrows(NullPointerException.class,() ->adressCRUDService.update(updatingAdress,adress.getId()));



    }

}
