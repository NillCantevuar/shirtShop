package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.practic.shirtshop.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
