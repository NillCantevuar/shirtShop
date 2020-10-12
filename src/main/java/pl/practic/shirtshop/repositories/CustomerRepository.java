package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.practic.shirtshop.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
