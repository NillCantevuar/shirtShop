//package pl.practic.shirtshop.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "order_line")
//public class OrderLine {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_line_id")
//    private Integer id;
//
//    @Column
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
//    private Product product;
//
//    @Column
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
//    private Order order;
//
//
//    @Column
//    private Integer quantity;
//
//}
