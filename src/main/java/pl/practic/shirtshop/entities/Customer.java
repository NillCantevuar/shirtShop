package pl.practic.shirtshop.entities;

import lombok.Data;
import pl.practic.shirtshop.dto.CustomerDTO;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

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
    private List<Order> orders;

    public Customer(String firstName, String lastName, Adress adress, Contact contact, List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.contact = contact;
        this.orders = orders;

    }
    public  Customer(){

    }


    public CustomerDTO toDTO() {

        Integer adressId = null;
        Integer contactId = null;
        List<Integer> ordersIds = null;

        if(adress!=null){
            adressId= adress.getId();
        }
        if(contact!=null){
            contactId = contact.getId();
        }
        if (orders !=null){
            if(!orders.isEmpty()){
                ordersIds = orders.stream()
                        .map(o -> o.getId())
                        .collect(Collectors.toList());
            }
        }
        return new CustomerDTO(id, firstName, lastName, adressId, contactId,
                ordersIds);
    }




}
