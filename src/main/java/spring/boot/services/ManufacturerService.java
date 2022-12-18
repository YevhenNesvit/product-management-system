package spring.boot.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.ManufacturerConverter;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.repositories.ManufacturerRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ManufacturerService {
    @Autowired
    private final ManufacturerRepository repository;
    @Autowired
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

    public boolean IsManufacturerNameExists(String name) {
        for (int i = 0; i < getManufacturers().size(); i++) {
            if(getManufacturers().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public UUID getManufacturerIdByName(String name) {
        List<ManufacturerDto> manufacturers = getManufacturers();
        UUID id = null;
        for (ManufacturerDto manufacturer : manufacturers) {
            if (manufacturer.getName().equals(name)) {
                id = manufacturer.getId();
            }
        }
        return id;
    }
}