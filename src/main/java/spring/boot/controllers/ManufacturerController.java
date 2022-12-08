package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.services.ManufacturerService;

@AllArgsConstructor
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/createManufacturerForm")
    public ModelAndView createManufacturerForm() {

        return new ModelAndView("createManufacturerForm");
    }

    @PostMapping("/manufacturerCreated")
    public ModelAndView addNewProduct(@ModelAttribute("manufacturerName") String manufacturerName, ManufacturerDao manufacturer
    ) {
        manufacturer.setName(manufacturerName);
        manufacturerService.create(manufacturer);

        return new ModelAndView("manufacturerCreated");
    }
}