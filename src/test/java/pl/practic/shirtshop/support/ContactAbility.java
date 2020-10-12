package pl.practic.shirtshop.support;

import org.springframework.stereotype.Component;
import pl.practic.shirtshop.entities.Contact;

@Component
public class ContactAbility {

    public Contact generateOneContact(){

        Contact contact = new Contact();
        contact.setPhoneNumber1("123456789");
        contact.setWww("www.cost.com");
        contact.setEmail("cvb@gmail.com");
        contact.setFax("564738291");
        contact.setPhoneNumber2("987654321");
        return contact;
    }
}
