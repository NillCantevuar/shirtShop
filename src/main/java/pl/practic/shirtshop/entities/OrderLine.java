package pl.practic.shirtshop.entities;

import lombok.Data;
import pl.practic.shirtshop.dto.OrderDTO;
import pl.practic.shirtshop.dto.OrderLineDTO;

import javax.persistence.*;
import java.util.stream.Collectors;

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

    public OrderLine(Product product, Order order, Integer quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public OrderLine() {

    }

    public OrderLineDTO toDTO() {

        Integer orderId = null;
        Integer productId = null;

        if (order != null) {
            orderId = order.getId();
        }
        if (product != null) {
            productId = product.getId();
        }

        return new OrderLineDTO(id, productId, orderId, quantity);
    }


}
