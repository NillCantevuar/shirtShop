package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
