package pl.practic.shirtshop.entities;


import lombok.Data;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.enums.ProductType;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column
    private ProductType type;

    @Column
    private String brand;

    @Column
    private Integer price;

    @Column
    private String name;

    @Column
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLine;

    public Product(Integer id, ProductType type, String brand, Integer price, String name, Integer stock, Object o) {
    }

    public ProductDTO toDTO() {
        return new ProductDTO(id,type.name(),brand,price,name,stock,
                orderLine.stream()
                        .map(OrderLine::getId)
                        .collect(Collectors.toList()));
    }

    public static Product fromDTO(Product dto) {
        return new Product(dto.getId(),
                dto.getType(),
                dto.getBrand(),
                dto.getPrice(),
                dto.getName(),
                dto.getStock(),
                null); //TODO
    }
}
