package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.services.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/createProductForm")
    public ModelAndView createProductForm() {

        return new ModelAndView("createProductForm");
    }

//    @PostMapping("/productCreated")
//    public String addNewProduct(
//            @ModelAttribute("productName") String productName,
//            @ModelAttribute("price") BigDecimal price,
//            @ModelAttribute("manufacturerId") UUID manufacturerId,
//            ProductDao product,
//            ManufacturerDao manufacturer
//    ) {
//        product.setName(productName);
//        product.setPrice(price);
//        product.setManufacturer(manufacturer);
//        productService.create(product);
//
//        return "main";
//    }
}
