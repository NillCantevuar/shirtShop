package pl.practic.shirtshop.dto;

import pl.practic.shirtshop.entities.Customer;

import javax.persistence.*;

public class AdressDTO {


    private Integer id;

    private String street;

    private String houseNumber;

    private String flatNumber;

    private String city;

    private String state;

    private String postalCode;

    private Integer customerId;

    public AdressDTO(Integer id, String street, String houseNumber, String flatNumber, String city, String state, String postalCode, Integer customerId) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.customerId = customerId;
    }

    public AdressDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
