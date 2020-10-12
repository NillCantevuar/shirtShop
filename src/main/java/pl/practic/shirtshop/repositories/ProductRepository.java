package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.practic.shirtshop.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
