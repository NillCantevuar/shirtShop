package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
