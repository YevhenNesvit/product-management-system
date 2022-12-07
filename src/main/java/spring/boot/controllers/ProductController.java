package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.services.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @GetMapping("/createProductForm")
    public ModelAndView createProduct() {

        return new ModelAndView("createProductForm");
    }
}
