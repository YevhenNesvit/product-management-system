package spring.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.services.ManufacturerService;

import java.util.List;
import java.util.UUID;

@Component
public class GetManufacturerNameById {
    @Autowired
    ManufacturerService manufacturerService;

    public String getManufacturerNameById(UUID id) {
        List<ManufacturerDto> manufacturers = manufacturerService.getManufacturers();
        String name = "";
        for (ManufacturerDto manufacturer : manufacturers) {
            if (manufacturer.getId().equals(id)) {
                name = manufacturer.getName();
            }
        }
        return name;
    }
}
