package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

}