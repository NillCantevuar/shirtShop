package pl.practic.shirtshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_line")
public class OrderLine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;


    @Column
    private Integer quantity;

}
