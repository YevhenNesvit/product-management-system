package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dto.ManufacturerDto;
import spring.boot.model.dto.ProductDto;
import spring.boot.services.ManufacturerService;
import spring.boot.services.ProductService;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ManufacturerService manufacturerService;

    @RolesAllowed("ADMIN")
    @GetMapping("/createProductForm")
    public ModelAndView createProductForm() {
        ModelAndView mav = new ModelAndView("products/createProductForm");
        mav.addObject("manufacturers", manufacturerService.getManufacturers());

        return mav;
    }

    @PostMapping("/productCreated")
    public ModelAndView createProduct(@ModelAttribute("productName") String productName, @ModelAttribute("price") BigDecimal price,
                                      @ModelAttribute("manufacturerName") String manufacturerName, ProductDto product,
                                      ManufacturerDto manufacturer) {

        if (productService.IsProductNameExists(productName)) {

            return new ModelAndView("products/productNameAlreadyExists");
        } else if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
            manufacturer.setId(manufacturerService.getManufacturerIdByName(manufacturerName));
            manufacturer.setName(manufacturerName);

            product.setName(productName);
            product.setPrice(price);
            product.setManufacturer(manufacturer);
            productService.create(product);

            return new ModelAndView("products/productCreated");
        } else {

            return new ModelAndView("manufacturers/manufacturerNameNotExists");
        }
    }

    @GetMapping("/getProducts")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView("products/getProducts");
        mav.addObject("products", productService.getProducts());

        return mav;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/deleteProductForm")
    public ModelAndView deleteProductForm() {

        return new ModelAndView("products/deleteProductForm");
    }

    @PostMapping("/productDeleted")
    public ModelAndView deleteManufacturer(@ModelAttribute("productName") String productName) {

        if (productService.IsProductNameExists(productName)) {
            productService.deleteByName(productName);

            return new ModelAndView("products/productDeleted");
        }

        return new ModelAndView("products/productNameNotExists");
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/updateProductForm")
    public ModelAndView updateProductForm() {

        return new ModelAndView("products/updateProductForm");
    }

    @PostMapping("/productUpdated")
    public ModelAndView updateProduct(@ModelAttribute("newName") String newName, @ModelAttribute("newPrice") BigDecimal newPrice,
                                      @ModelAttribute("manufacturerName") String manufacturerName,
                                      @ModelAttribute("oldName") String oldName, ManufacturerDto manufacturer) {
        if (productService.IsProductNameExists(oldName)) {
            if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
                manufacturer.setId(manufacturerService.getManufacturerIdByName(manufacturerName));
                manufacturer.setName(manufacturerName);
                productService.updateByName(newName, newPrice, manufacturer, oldName);

                return new ModelAndView("products/productUpdated");
            } else {

                return new ModelAndView("manufacturers/manufacturerNameNotExists");
            }
        } else {

            return new ModelAndView("products/productNameNotExists");
        }
    }
}
