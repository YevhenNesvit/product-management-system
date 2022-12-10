package spring.boot.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.services.ProductService;

@AllArgsConstructor
@Component
public class CheckProducts {
    @Autowired
    private final ProductService productService;

    public boolean IsProductNameExists(String name) {
        for (int i = 0; i < productService.getProducts().size(); i++) {
            if (productService.getProducts().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
