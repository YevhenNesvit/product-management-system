package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.services.ManufacturerService;
import spring.boot.utils.CheckManufacturers;

@AllArgsConstructor
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    CheckManufacturers checkManufacturers;

    @GetMapping("/createManufacturerForm")
    public ModelAndView createManufacturers() {

        return new ModelAndView("manufacturers/createManufacturerForm");
    }

    @PostMapping("/manufacturerCreated")
    public ModelAndView addNewManufacturer(@ModelAttribute("manufacturerName") String manufacturerName, ManufacturerDao manufacturer
    ) {
        if (checkManufacturers.IsManufacturerNameExists(manufacturerName)) {

            return new ModelAndView("manufacturers/manufacturerNameAlreadyExists");
        }

        manufacturer.setName(manufacturerName);
        manufacturerService.create(manufacturer);

        return new ModelAndView("manufacturers/manufacturerCreated");
    }

    @GetMapping("/getManufacturers")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView("manufacturers/getManufacturers");
        mav.addObject("manufacturers", manufacturerService.getManufacturers());

        return mav;
    }

    @GetMapping("/deleteManufacturerForm")
    public ModelAndView deleteManufacturers() {

        return new ModelAndView("manufacturers/deleteManufacturerForm");
    }

    @PostMapping("/manufacturerDeleted")
    public ModelAndView deleteManufacturer(@ModelAttribute("manufacturerName") String manufacturerName) {

        if (checkManufacturers.IsManufacturerNameExists(manufacturerName)) {
            manufacturerService.deleteByName(manufacturerName);

            return new ModelAndView("manufacturers/manufacturerDeleted");
        }

        return new ModelAndView("manufacturers/manufacturerNameNotExists");
    }
}