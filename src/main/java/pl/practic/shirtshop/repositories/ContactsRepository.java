package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts,Integer> {

}
