package pl.practic.shirtshop.dto;


import pl.practic.shirtshop.enums.ProductType;

import java.util.List;

public class ProductDTO {

    private Integer id;

    private String type;

    private String brand;

    private Integer price;

    private String name;

    private Integer stock;

    private List<Integer> orderLinesId;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, ProductType type, String brand, Integer price, String name, Integer stock, List<Integer> orderLinesId) {
        this.id = id;
        this.type = type.name();
        this.brand = brand;
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.orderLinesId = orderLinesId;
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

    public List<Integer> getOrderLinesId() {
        return orderLinesId;
    }

    public void setOrderLinesId(List<Integer> orderLinesId) {
        this.orderLinesId = orderLinesId;
    }
}
