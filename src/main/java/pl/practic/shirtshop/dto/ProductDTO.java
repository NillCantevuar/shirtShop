package pl.practic.shirtshop.dto;


import lombok.Data;
import pl.practic.shirtshop.entities.OrderLine;
import pl.practic.shirtshop.enums.ProductType;

import javax.persistence.*;
import java.util.List;

public class ProductDTO {

    private Integer id;

    private String type;

    private String brand;

    private Integer price;

    private String name;

    private Integer stock;

    private List<Integer> orderLineId;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, ProductType type, String brand, Integer price, String name, Integer stock, List<Integer> orderLineId) {
        this.id = id;
        this.type = type.name();
        this.brand = brand;
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.orderLineId = orderLineId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<Integer> getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(List<Integer> orderLineId) {
        this.orderLineId = orderLineId;
    }
}
