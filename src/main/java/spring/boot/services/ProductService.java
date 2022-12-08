package spring.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.model.dao.ProductDao;

import spring.boot.repositories.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    private final ProductRepository repository;

    public void create(ProductDao product) {
        repository.save(product);
    }

}
