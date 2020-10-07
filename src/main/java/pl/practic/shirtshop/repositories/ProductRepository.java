package pl.practic.shirtshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.practic.shirtshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
