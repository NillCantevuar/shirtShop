package pl.practic.shirtshop.entities;


import lombok.Data;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.dto.CustomerDTO;
import pl.practic.shirtshop.dto.OrderDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order(Integer id, Customer customer, LocalDateTime dateTime, String status, List<OrderLine> orderLines) {
    }
    public Order(){

    }

    public OrderDTO toDTO() {
        return new OrderDTO(id, customer.getId(), dateTime, status,
                orderLines.stream()
                        .map(OrderLine::getId)
                        .collect(Collectors.toList()));
    }


}
