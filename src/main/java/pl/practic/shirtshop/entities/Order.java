package pl.practic.shirtshop.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;


    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderLine> orderLines;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column
    private String status;

}
