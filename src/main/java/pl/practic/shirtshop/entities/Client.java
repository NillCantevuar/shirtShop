package pl.practic.shirtshop.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private  Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "adress_id")
    private Integer adressId;

    @Column(name = "contact_id")
    private Integer contactId;

    @Column(name = "client_rating")
    private Integer clientRating;


}
