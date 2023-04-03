package spring.boot.manufacturer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    private final ManufacturerService manufacturerService;

    @GetMapping("/createManufacturerForm")
    public ModelAndView createManufacturerForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("ManufacturerDto", new ManufacturerDto());

        return mav;
    }

    @PostMapping("/createManufacturerForm")
    public ModelAndView createManufacturer(@ModelAttribute("ManufacturerDto") @Valid ManufacturerDto manufacturer,
                                           BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("manufacturers/createManufacturerForm");
        } else if (manufacturerService.IsManufacturerNameExists(manufacturer.getName())) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("manufacturerExists", "Manufacturer already exists!");

            return mav;
        } else {
            ModelAndView mav = new ModelAndView("redirect:/manufacturers/getManufacturers");
            redirect.addFlashAttribute("creation", "Manufacturer successfully created!");

            model.addAttribute("ManufacturerDto", manufacturer);

            manufacturerService.create(manufacturer);

            return mav;
        }
    }

    @GetMapping("/getManufacturers")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("manufacturers", manufacturerService.getManufacturers());

        return mav;
    }

    @GetMapping("/deleteManufacturerForm")
    public ModelAndView deleteManufacturerForm() {

        return new ModelAndView("manufacturers/deleteManufacturerForm");
    }

    @PostMapping("/deleteManufacturerForm")
    public ModelAndView deleteManufacturer(@ModelAttribute("manufacturerName") String manufacturerName, RedirectAttributes redirect) {

        if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
            ModelAndView mav = new ModelAndView("redirect:/manufacturers/getManufacturers");
            redirect.addFlashAttribute("deletion", "Manufacturer successfully deleted!");

            manufacturerService.deleteByName(manufacturerName);

            return mav;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("manufacturerDoesNotExists", "Manufacturer does not exist!");

        return mav;
    }

    @GetMapping("/updateManufacturerForm")
    public ModelAndView updateManufacturerForm() {
        ModelAndView mav = new ModelAndView();
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
            ModelAndView mav = new ModelAndView();
            mav.addObject("manufacturerDoesNotExists", "Manufacturer does not exist!");

            return mav;
        }
    }
}
