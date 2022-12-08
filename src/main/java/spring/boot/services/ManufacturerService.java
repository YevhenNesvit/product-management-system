package spring.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.repositories.ManufacturerRepository;

@RequiredArgsConstructor
@Service
public class ManufacturerService {
    @Autowired
    private final ManufacturerRepository repository;

    public void create(ManufacturerDao manufacturer) {
        repository.save(manufacturer);
    }

}