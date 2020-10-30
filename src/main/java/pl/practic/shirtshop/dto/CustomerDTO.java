package pl.practic.shirtshop.dto;

import java.util.List;

public class CustomerDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer adressId;

    private Integer contactId;

    private List<Integer> orderId;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer id, String firstName, String lastName, Integer adressId, Integer contactId, List<Integer> orderId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adressId = adressId;
        this.contactId = contactId;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAdressId() {
        return adressId;
    }

    public void setAdressId(Integer adressId) {
        this.adressId = adressId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public List<Integer> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Integer> orderId) {
        this.orderId = orderId;
    }
}
