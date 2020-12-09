package pl.practic.shirtshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.entities.Contact;
import pl.practic.shirtshop.interfaces.CRUDService;
import pl.practic.shirtshop.mappers.DTOMapper;
import pl.practic.shirtshop.repositories.ContactRepository;
import pl.practic.shirtshop.repositories.CustomerRepository;

@Service
public class ContactCRUDService implements CRUDService<ContactDTO> {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DTOMapper dtoMapper;


    @Override
    public ContactDTO find(Integer id) {
        return contactRepository.getOne(id).toDTO();
    }

    @Override
    public Integer save(ContactDTO contact) {
        Contact saved = contactRepository.save(dtoMapper.fromContactDTO(contact));
        return saved.getId();
    }


    @Override
    public Integer update(ContactDTO contact, Integer id) {

        Contact pulledContact = contactRepository.getOne(id);

        if(contact.getCustomerId() != null) {
            pulledContact.setPhoneNumber1(contact.getPhoneNumber1());
            pulledContact.setPhoneNumber2(contact.getPhoneNumber2());
            pulledContact.setEmail(contact.getEmail());
            pulledContact.setWww(contact.getWww());
            pulledContact.setFax(contact.getFax());
            pulledContact.setCustomer(customerRepository.getOne(contact.getCustomerId()));
            contactRepository.save(pulledContact);
            return id;
        }
        pulledContact.setPhoneNumber1(contact.getPhoneNumber1());
        pulledContact.setPhoneNumber2(contact.getPhoneNumber2());
        pulledContact.setEmail(contact.getEmail());
        pulledContact.setWww(contact.getWww());
        pulledContact.setFax(contact.getFax());
        pulledContact.setCustomer(null);
        contactRepository.save(pulledContact);
        return id;

    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }

}
