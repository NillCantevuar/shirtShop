package pl.practic.shirtshop.requests;

public class ProductAddRequest {

    private String type;

    private String brand;

    private Integer price;

    private String name;

    private Integer stock;

    public ProductAddRequest(String type, String brand, Integer price, String name, Integer stock) {
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.name = name;
        this.stock = stock;
    }
    public ProductAddRequest() {
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
}
