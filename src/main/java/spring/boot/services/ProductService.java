package spring.boot.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.ProductConverter;

import spring.boot.model.dto.ManufacturerDto;
import spring.boot.model.dto.ProductDto;
import spring.boot.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    @Autowired
    private final ProductRepository repository;
    @Autowired
    private final ProductConverter converter;

    public void create(ProductDto product) {
        repository.save(converter.to(product));
    }

    public List<ProductDto> getProducts() {
        return converter.fromList(repository.findAll());
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public void updateByName(String newName, BigDecimal newPrice, ManufacturerDto newManufacturer, String oldName) {
        repository.updateByName(newName, newPrice, newManufacturer, oldName);
    }

    public boolean IsProductNameExists(String name) {
        for (int i = 0; i < getProducts().size(); i++) {
            if (getProducts().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
