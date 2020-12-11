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

    public Order( Customer customer, LocalDateTime dateTime, String status, List<OrderLine> orderLines) {
        this.customer = customer;
        this.dateTime = dateTime;
        this.status = status;
        this.orderLines = orderLines;


    }
    public Order(){

    }

    public OrderDTO toDTO() {

        Integer customerId = null;
        List<Integer> orderLinesIds = null;

        if (customer != null){
            customerId = customer.getId();
        }
        if (orderLines!=null){
            if(!orderLines.isEmpty()){
                orderLinesIds = orderLines.stream()
                        .map(OrderLine::getId)
                        .collect(Collectors.toList());
            }
        }



        return new OrderDTO(id, customerId, dateTime, status,
                orderLinesIds);
    }


}
