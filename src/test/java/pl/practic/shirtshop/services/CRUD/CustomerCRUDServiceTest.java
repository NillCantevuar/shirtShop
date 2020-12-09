package pl.practic.shirtshop.services.CRUD;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.AdressRepository;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;
import pl.practic.shirtshop.repositories.OrderRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerCRUDServiceTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AdressRepository adressRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DTOMapper dtoMapper;

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerAloneUsignId_CompareFields_CR() {
    }

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerWithAdressUsignId_CompareFields_CR() {
    }

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerWithContactUsignId_CompareFields_CR() {
    }

    @Test
    @Transactional
    public void shouldSaveAndFindCustomerWithContactAndAdressUsignId_CompareFields_CR() {
    }







    @Test
    @Transactional
    public void shouldUpdateCustomer_CompareFields_U() {
    }
    @Test
    @Transactional
    public void shouldUpdateCustomerWithContact_CompareFields_U() {
    }
    @Test
    @Transactional
    public void shouldUpdateCustomerWithAdress_CompareFields_U() {
    }
    @Test
    @Transactional
    public void shouldUpdateCustomerWithContactAndAdress_CompareFields_U() {
    }

    @Test
    @Transactional
    public void shouldSaveAndDeleteCustomerUsignId_isNull_D() {
    }


}
