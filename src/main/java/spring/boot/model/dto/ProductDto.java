package spring.boot.model.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class ProductDto {
    private UUID Id;
    @NotBlank
    private String name;
    @Digits(integer = 9, fraction = 2, message = "Wrong format of price")
    private BigDecimal price;
    private ManufacturerDto manufacturer;

    public ProductDto() {

    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }
}
