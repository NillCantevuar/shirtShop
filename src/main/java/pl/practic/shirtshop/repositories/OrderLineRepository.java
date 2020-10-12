package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.practic.shirtshop.entities.OrderLine;
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}
