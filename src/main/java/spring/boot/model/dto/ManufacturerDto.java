package spring.boot.model.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class ManufacturerDto {
    private UUID Id;
    @NotBlank
    private String name;
    private Set<ProductDto> products;

    public ManufacturerDto() {

    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }
}
