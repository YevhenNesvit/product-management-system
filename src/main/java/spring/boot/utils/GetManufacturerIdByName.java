package spring.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.services.ManufacturerService;

import java.util.List;
import java.util.UUID;

@Component
public class GetManufacturerIdByName {
    @Autowired
    ManufacturerService manufacturerService;

    public UUID getManufacturerIdByName(String name) {
        List<ManufacturerDto> manufacturers = manufacturerService.getManufacturers();
        UUID id = null;
        for (ManufacturerDto manufacturer : manufacturers) {
            if (manufacturer.getName().equals(name)) {
                id = manufacturer.getId();
            }
        }
        return id;
    }
}
