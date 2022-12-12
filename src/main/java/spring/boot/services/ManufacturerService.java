package spring.boot.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.ManufacturerConverter;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.repositories.ManufacturerRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ManufacturerService {
    @Autowired
    private final ManufacturerRepository repository;
    private final ManufacturerConverter converter;

    public void create(ManufacturerDao manufacturer) {
        repository.save(manufacturer);
    }

    public List<ManufacturerDto> getManufacturers() {
        return converter.fromList(repository.findAll());
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public void updateByName(String newName, String oldName) {
        repository.updateByName(newName, oldName);
    }
}