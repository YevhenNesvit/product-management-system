package spring.boot.product;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.ProductConverter;

import java.util.List;
import java.util.UUID;

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

    public boolean IsProductNameExists(String name) {
        for (int i = 0; i < getProducts().size(); i++) {
            if (getProducts().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public UUID getProductIdByName(String name) {
        List<ProductDto> products = getProducts();
        UUID id = null;
        for (ProductDto product : products) {
            if (product.getName().equals(name)) {
                id = product.getId();
            }
        }
        return id;
    }
}
