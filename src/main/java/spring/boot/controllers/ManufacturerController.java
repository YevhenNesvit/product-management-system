package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.services.ManufacturerService;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/createManufacturerForm")
    public ModelAndView createManufacturerForm() {
        ModelAndView mav = new ModelAndView("manufacturers/createManufacturerForm");
        mav.addObject("ManufacturerDto", new ManufacturerDto());

        return mav;
    }

    @PostMapping("/createManufacturerForm")
    public ModelAndView createManufacturer(@ModelAttribute("ManufacturerDto") @Valid ManufacturerDto manufacturer,
                                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("manufacturers/createManufacturerForm");
        } else if (manufacturerService.IsManufacturerNameExists(manufacturer.getName())) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("manufacturerExists", "Manufacturer already exists!");

            return mav;
        } else {
            model.addAttribute("ManufacturerDto", manufacturer);
            manufacturerService.create(manufacturer);

            return new ModelAndView("manufacturers/manufacturerCreated");
        }
    }

    @GetMapping("/getManufacturers")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView("manufacturers/getManufacturers");
        mav.addObject("manufacturers", manufacturerService.getManufacturers());

        return mav;
    }

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

    @GetMapping("/updateManufacturerForm")
    public ModelAndView updateManufacturerForm() {
        ModelAndView mav = new ModelAndView("manufacturers/updateManufacturerForm");
        mav.addObject("ManufacturerDto", new ManufacturerDto());

        return mav;
    }

    @PostMapping("/updateManufacturerForm")
    public ModelAndView updateManufacturer(@ModelAttribute("ManufacturerDto") @Valid ManufacturerDto manufacturer,
                                           BindingResult bindingResult, Model model, @ModelAttribute("oldName") String oldName) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("manufacturers/updateManufacturerForm");
        } else if (manufacturerService.IsManufacturerNameExists(oldName)) {
            model.addAttribute("ManufacturerDto", manufacturer);
            manufacturer.setId(manufacturerService.getManufacturerIdByName(oldName));
            manufacturerService.create(manufacturer);

            return new ModelAndView("manufacturers/manufacturerUpdated");
        } else {

            return new ModelAndView("manufacturers/manufacturerNameNotExists");
        }
    }
}