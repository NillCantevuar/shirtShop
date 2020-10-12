package pl.practic.shirtshop.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.practic.shirtshop.entities.Contact;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IntegralEntityTest {

    @Test
    @Transactional
    public void ConsumerOrderedThreDiffrentProducts(){

        Contact contact = new Contact();
        contact.setPhoneNumber1("123456789");
        contact.setWww("www.cost.com");
        contact.setEmail("cvb@gmail.com");
        contact.setFax("564738291");
        contact.setPhoneNumber2("987654321");




    }

}
