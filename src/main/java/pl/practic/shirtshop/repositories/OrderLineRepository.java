package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}
