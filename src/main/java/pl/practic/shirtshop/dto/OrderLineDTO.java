package pl.practic.shirtshop.dto;

import lombok.Data;
import pl.practic.shirtshop.entities.Order;
import pl.practic.shirtshop.entities.Product;

import javax.persistence.*;

public class OrderLineDTO {


    private Integer id;


    private Integer productId;


    private Integer orderId;


    private Integer quantity;

    public OrderLineDTO() {
    }

    public OrderLineDTO(Integer id, Integer productId, Integer orderId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
