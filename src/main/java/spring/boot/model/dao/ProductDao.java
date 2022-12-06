package spring.boot.model.dao;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Product")
@Table(name = "products")
public class ProductDao {
    private UUID id;
    private String name;
    private BigDecimal price;
    private ManufacturerDao manufacturer;

    public ProductDao() {

    }

    @Id
    @Column(name = "product_id")
    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "product_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer_name")
    public ManufacturerDao getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDao manufacturer) {
        this.manufacturer = manufacturer;
    }
}
