package pl.practic.shirtshop.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Adress;
import pl.practic.shirtshop.entities.Customer;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdressEntityTest {

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    CustomerRepository customerRepository;


//    @Test
//    @Transactional
//    public void shouldAddAndGetOneAdress_StreetCityHouseNumber(){
//
//        //given
//        Adress adress = new Adress();
//        String street = "Dluga";
//        String city = "Lublin";
//        String houseNumber = "23B";
//
//        adress.setStreet(street);
//        adress.setCity(city);
//        adress.setHouseNumber(houseNumber);
//        //when
//        adressRepository.save(adress);
//        Adress adress2 = adressRepository.getOne(adress.getId());
//        //then
//        Assert.assertEquals(street,adress2.getStreet());
//        Assert.assertEquals(city,adress2.getCity());
//        Assert.assertEquals(houseNumber,adress2.getHouseNumber());
//
//    }
//
//    @Test
//    @Transactional
//    public void shouldAddAndGetOneAdressInsideCustomer_ConnectionTest_StreetCityHouseNumberCheck(){
//
//        //given
//        Customer customer = new Customer();
//        Adress adress = new Adress();
//
//        String firstName = "Harry";
//        String lastName = "Kane";
//        customer.setFirstName(firstName);
//        customer.setLastName(lastName);
//
//        String street = "Dluga";
//        String city = "Lublin";
//        String houseNumber = "23B";
//        adress.setStreet(street);
//        adress.setCity(city);
//        adress.setHouseNumber(houseNumber);
//
//        customer.setAdress(adress);
//        //when
//        customerRepository.save(customer);
//        Adress adressPulled = adressRepository.getOne(adress.getId());
//        //then
//        Assert.assertEquals(street,adressPulled.getStreet());
//        Assert.assertEquals(city,adressPulled.getCity());
//        Assert.assertEquals(houseNumber,adressPulled.getHouseNumber());
//
//    }
//    @Test
//    @Transactional
//    public void shouldAddAndGetOneAdressInsideCustomer_GetFromCustomer_StreetCityHouseNumberCheck(){
//
//        //given
//        Customer customer = new Customer();
//        Adress adress = new Adress();
//
//        String firstName = "Harry";
//        String lastName = "Kane";
//        customer.setFirstName(firstName);
//        customer.setLastName(lastName);
//
//        String street = "Dluga";
//        String city = "Lublin";
//        String houseNumber = "23B";
//        adress.setStreet(street);
//        adress.setCity(city);
//        adress.setHouseNumber(houseNumber);
//
//        customer.setAdress(adress);
//        //when
//        customerRepository.save(customer);
//        Customer customerPulled = customerRepository.getOne(customer.getId());
//        Adress adressPulled = customerPulled.getAdress();
//        //then
//        Assert.assertEquals(street,adressPulled.getStreet());
//        Assert.assertEquals(city,adressPulled.getCity());
//        Assert.assertEquals(houseNumber,adressPulled.getHouseNumber());
//
//    }
}
