package spring.boot.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.services.ManufacturerService;

@AllArgsConstructor
@Component
public class CheckManufacturers {
    @Autowired
    private final ManufacturerService manufacturerService;

    public boolean IsManufacturerNameExists(String name) {
        for (int i = 0; i < manufacturerService.getManufacturers().size(); i++) {
            if(manufacturerService.getManufacturers().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
