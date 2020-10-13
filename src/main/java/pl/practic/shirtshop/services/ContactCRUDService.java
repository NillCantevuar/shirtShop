package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.ContactRepository;

@Service
public class ContactCRUDService implements CRUDService<Contact> {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact find(int id) {
        return contactRepository.getOne(id);
    }

    @Override
    public int save(Contact contact) {
        contactRepository.save(contact);
        return contact.getId();
    }


    @Override
    public int update(Contact contact, int id) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

}
