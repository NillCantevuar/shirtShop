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

    public Contact(Integer id, String phoneNumber1, String phoneNumber2, String email, String www, String fax, Customer customer) {
    }

    public ContactDTO toDTO() {
        return new ContactDTO(id, phoneNumber1, phoneNumber2, email, www, fax, customer.getId());
    }

    public static Contact fromDTO(ContactDTO dto) {
        return new Contact(dto.getId(),
                dto.getPhoneNumber1(),
                dto.getPhoneNumber2(),
                dto.getEmail(),
                dto.getWww(),
                dto.getFax(),
                null); //TODO
    }

}
