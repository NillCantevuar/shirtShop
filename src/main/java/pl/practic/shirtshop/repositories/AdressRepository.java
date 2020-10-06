package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.Adress;

public interface AdressRepository extends JpaRepository<Adress,Integer> {

}
