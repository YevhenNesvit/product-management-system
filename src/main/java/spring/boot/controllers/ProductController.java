package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.model.dao.ProductDao;
import spring.boot.services.ManufacturerService;
import spring.boot.services.ProductService;
import spring.boot.utils.GetManufacturerNameById;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ManufacturerService manufacturerService;
    @Autowired
    GetManufacturerNameById manufacturerNameById;

    @GetMapping("/createProductForm")
    public ModelAndView createProductForm() {

        return new ModelAndView("products/createProductForm");
    }

    @PostMapping("/productCreated")
    public ModelAndView createProduct(@ModelAttribute("productName") String productName, @ModelAttribute("price") BigDecimal price,
            @ModelAttribute("manufacturerId") UUID manufacturerId, ProductDao product, ManufacturerDao manufacturer) {
        manufacturer.setId(manufacturerId);
        manufacturer.setName(manufacturerNameById.getManufacturerNameById(manufacturerId));

        product.setName(productName);
        product.setPrice(price);
        product.setManufacturer(manufacturer);
        productService.create(product);

        return new ModelAndView("products/productCreated");
    }
}
