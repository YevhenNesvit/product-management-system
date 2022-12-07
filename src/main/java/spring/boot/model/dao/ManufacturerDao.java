package spring.boot.model.dao;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Manufacturer")
@Table(name = "manufacturers")
public class ManufacturerDao {
    private UUID id;
    private String name;
    private Set<ProductDao> products;

    public ManufacturerDao() {

    }

    @Id
    @Column(name = "manufacturer_id")
    @GeneratedValue
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "manufacturer_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "manufacturer")
    public Set<ProductDao> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDao> products) {
        this.products = products;
    }
}
