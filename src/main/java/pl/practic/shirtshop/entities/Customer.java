package pl.practic.shirtshop.entities;

import lombok.Data;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Collection;
import pl.practic.shirtshop.dto.ContactDTO;
import pl.practic.shirtshop.dto.CustomerDTO;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Order> order;

    public Customer(Integer id, String firstName, String lastName, Adress adress, Contact contact, List<Order> order) {
    }


    public CustomerDTO toDTO() {
        return new CustomerDTO(id,firstName,lastName,adress.getId(),contact.getId(),
                order.stream()
                        .map(o->o.getId())
                .collect(Collectors.toList()));
    }

    public static Customer fromDTO(CustomerDTO dto) {
        return new Customer(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                null,
                null,
                null); //TODO
    }


}
