package pl.practic.shirtshop.entities;


import lombok.Data;
import pl.practic.shirtshop.dto.AdressDTO;
import pl.practic.shirtshop.dto.ContactDTO;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
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

    @OneToOne(mappedBy = "contact")
    private Customer customer;

    public Contact(String phoneNumber1, String phoneNumber2, String email, String www, String fax, Customer customer) {
       this.phoneNumber1 = phoneNumber1;
       this.phoneNumber2 = phoneNumber2;
       this.email = email;
       this.www = www;
       this.fax = fax;
       this.customer = customer;


    }
    public Contact (){

    }

    public ContactDTO toDTO() {
        if(customer != null) {
            return new ContactDTO(id, phoneNumber1, phoneNumber2, email, www, fax, customer.getId());
        }
        return new ContactDTO(id, phoneNumber1, phoneNumber2, email, www, fax, null);
    }



}
