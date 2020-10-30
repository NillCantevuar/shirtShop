package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

@Service
public class ContactCRUDService implements CRUDService<ContactDTO> {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public ContactDTO find(int id) {
        return contactRepository.getOne(id).toDTO();
    }

    @Override
    public int save(ContactDTO contact) {
        Contact saved = contactRepository.save(Contact.fromDTO(contact));
        return saved.getId();
    }


    @Override
    public ContactDTO update(ContactDTO contact, int id) {
        Contact pulledContact = contactRepository.getOne(id);
        pulledContact.setPhoneNumber1(contact.getPhoneNumber1());
        pulledContact.setPhoneNumber2(contact.getPhoneNumber2());
        pulledContact.setEmail(contact.getEmail());
        pulledContact.setWww(contact.getWww());
        pulledContact.setFax(contact.getFax());
        pulledContact.setCustomer(customerRepository.getOne(contact.getCustomerId()));
        Contact saved = contactRepository.save(pulledContact);
        return saved.toDTO();
    }

    @Override
    public void delete(int id) {
        contactRepository.deleteById(id);
    }

}
