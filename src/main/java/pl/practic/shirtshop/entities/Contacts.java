package pl.practic.shirtshop.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacts_id")
    private Integer id;

    @Column(name = "phone_number1")
    private String phoneNumber1;

    @Column(name = "phone_number2")
    private String phoneNumber2;

    @Column(name = "e-mail")
    private String email;

    @Column(name = "www")
    private String www;

    @Column(name = "fax")
    private String fax;

    @OneToOne(mappedBy = "contacts")
    private Customer customer;
}
