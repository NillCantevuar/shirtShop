package pl.practic.shirtshop.entities;

import lombok.Data;
import pl.practic.shirtshop.dto.AdressDTO;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id")
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "flat_number")
    private String flatNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne(mappedBy = "adress")
    private Customer customer;

    public Adress(String street, String houseNumber, String flatNumber, String city, String state, String postalCode, Customer customer) {

        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.customer = customer;
    }
    public Adress(){

    }

    public AdressDTO toDTO() {
        if (customer != null) {
            return new AdressDTO(id, street, houseNumber, flatNumber, city, state, postalCode, customer.getId());
        }
        return new AdressDTO(id, street, houseNumber, flatNumber, city, state, postalCode, null);
    }

}
