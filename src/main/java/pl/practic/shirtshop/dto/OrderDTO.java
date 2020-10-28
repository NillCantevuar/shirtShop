package pl.practic.shirtshop.dto;


import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Integer id;

    private Integer customerId;

    private LocalDateTime dateTime;

    private String status;

    private List<Integer> orderLinesId;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer customerId, LocalDateTime dateTime, String status, List<Integer> orderLinesId) {
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.status = status;
        this.orderLinesId = orderLinesId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getOrderLinesId() {
        return orderLinesId;
    }

    public void setOrderLinesId(List<Integer> orderLinesId) {
        this.orderLinesId = orderLinesId;
    }


}
