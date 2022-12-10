package spring.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.ProductConverter;
import spring.boot.model.dao.ProductDao;

import spring.boot.model.dto.ProductDto;
import spring.boot.repositories.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    private final ProductRepository repository;
    @Autowired
    private final ProductConverter converter;

    public void create(ProductDao product) {
        repository.save(product);
    }

    public List<ProductDto> getProducts() {
        return converter.fromList(repository.findAll());
    }
}
