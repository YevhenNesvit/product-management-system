package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.services.ManufacturerService;

import javax.annotation.security.RolesAllowed;

@AllArgsConstructor
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @RolesAllowed("ADMIN")
    @GetMapping("/createManufacturerForm")
    public ModelAndView createManufacturerForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("ManufacturerDto", new ManufacturerDto());
        mav.setViewName("manufacturers/createManufacturerForm");
        return mav;
    }

    @PostMapping("/manufacturerCreated")
    public ModelAndView createManufacturer(@ModelAttribute ManufacturerDto manufacturer) {
        if (manufacturerService.IsManufacturerNameExists(manufacturer.getName())) {

            return new ModelAndView("manufacturers/manufacturerNameAlreadyExists");
        }

        manufacturerService.create(manufacturer);

        return new ModelAndView("manufacturers/manufacturerCreated");
    }

    @GetMapping("/getManufacturers")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView("manufacturers/getManufacturers");
        mav.addObject("manufacturers", manufacturerService.getManufacturers());

        return mav;
    }
    @RolesAllowed("ADMIN")
    @GetMapping("/deleteManufacturerForm")
    public ModelAndView deleteManufacturerForm() {

        return new ModelAndView("manufacturers/deleteManufacturerForm");
    }

    @PostMapping("/manufacturerDeleted")
    public ModelAndView deleteManufacturer(@ModelAttribute("manufacturerName") String manufacturerName) {

        if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
            manufacturerService.deleteByName(manufacturerName);

            return new ModelAndView("manufacturers/manufacturerDeleted");
        }

        return new ModelAndView("manufacturers/manufacturerNameNotExists");
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/updateManufacturerForm")
    public ModelAndView updateManufacturerForm() {

        return new ModelAndView("manufacturers/updateManufacturerForm");
    }

    @PostMapping("/manufacturerUpdated")
    public ModelAndView updateManufacturer(@ModelAttribute("newName") String newName, @ModelAttribute("oldName") String oldName) {

        if (manufacturerService.IsManufacturerNameExists(oldName)) {
            manufacturerService.updateByName(newName, oldName);

            return new ModelAndView("manufacturers/manufacturerUpdated");
        }

        return new ModelAndView("manufacturers/manufacturerNameNotExists");
    }
}