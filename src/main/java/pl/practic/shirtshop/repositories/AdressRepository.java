package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.practic.shirtshop.entities.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer> {

}
