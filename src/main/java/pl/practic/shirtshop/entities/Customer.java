package pl.practic.shirtshop.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private  Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id")
    private Adress adress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id")
    private Contacts contacts;
//
//    @Column(name = "client_rating")
////    private Integer clientRating;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
//    private List<Order> order;


}
