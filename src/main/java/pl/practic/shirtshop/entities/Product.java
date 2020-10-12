package pl.practic.shirtshop.entities;


import lombok.Data;
import pl.practic.shirtshop.enums.ProductType;

import javax.persistence.*;
import java.util.List;

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
}
