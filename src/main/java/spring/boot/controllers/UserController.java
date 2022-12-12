package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.model.dao.ProductDao;
import spring.boot.services.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/createUserForm")
    public ModelAndView createUserForm() {
        ModelAndView mav = new ModelAndView("users/createUserForm");
        mav.addObject("users", userService.getUsers());

        return mav;
    }

    @PostMapping("/userCreated")
    public ModelAndView createUser(@ModelAttribute("userEmail") String userEmail, @ModelAttribute("password") String password,
                                      @ModelAttribute("userFirstName") String userFirstName, ProductDao product,
                                      ManufacturerDao manufacturer) {

        if (checkProducts.IsProductNameExists(productName)) {

            return new ModelAndView("products/productNameAlreadyExists");
        }
        manufacturer.setId(manufacturerId.getManufacturerIdByName(manufacturerName));
        manufacturer.setName(manufacturerName);

        product.setName(productName);
        product.setPrice(price);
        product.setManufacturer(manufacturer);
        productService.create(product);

        return new ModelAndView("products/productCreated");
    }

    @GetMapping("/getUsers")
    public ModelAndView getUsers() {
        ModelAndView mav = new ModelAndView("users/getUsers");
        mav.addObject("users", userService.getUsers());

        return mav;
    }

}
