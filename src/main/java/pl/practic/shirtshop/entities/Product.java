package pl.practic.shirtshop.entities;


import lombok.Data;
import pl.practic.shirtshop.dto.ProductDTO;
import pl.practic.shirtshop.enums.ProductType;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "product")
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
    private List<OrderLine> orderLines;

    public Product(ProductType type, String brand, Integer price, String name, Integer stock, List<OrderLine> orderLines) {
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.orderLines = orderLines;
    }
    public Product(){}

    public ProductDTO toDTO() {
        if(orderLines != null) {
            return new ProductDTO(id, type.name(), brand, price, name, stock,
                    orderLines.stream()
                            .map(OrderLine::getId)
                            .collect(Collectors.toList()));
        }
        return new ProductDTO(id,type.name(),brand,price,name,stock,null);
    }


}
