package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.services.ManufacturerService;
import spring.boot.services.ProductService;

import javax.annotation.security.RolesAllowed;

@AllArgsConstructor
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    private final ManufacturerService manufacturerService;
    @Autowired
    private final ProductService productService;

    @RolesAllowed("ADMIN")
    @GetMapping("/createManufacturerForm")
    public ModelAndView createManufacturerForm() {

        return new ModelAndView("manufacturers/createManufacturerForm");
    }

    @PostMapping("/manufacturerCreated")
    public ModelAndView createManufacturer(@ModelAttribute("manufacturerName") String manufacturerName, ManufacturerDao manufacturer
    ) {
        if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {

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
    @RolesAllowed("ADMIN")
    @GetMapping("/deleteManufacturerForm")
    public ModelAndView deleteManufacturerForm() {

        return new ModelAndView("manufacturers/deleteManufacturerForm");
    }

    @PostMapping("/manufacturerDeleted")
    public ModelAndView deleteManufacturer(@ModelAttribute("manufacturerName") String manufacturerName) {

        if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
            if (!productService.IsProductWithManufacturerExists(manufacturerName)) {
                manufacturerService.deleteByName(manufacturerName);

                return new ModelAndView("manufacturers/manufacturerDeleted");
            } else {

                return new ModelAndView("manufacturers/productWithThisManufacturerExists");
            }
        } else {

            return new ModelAndView("manufacturers/manufacturerNameNotExists");
        }
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