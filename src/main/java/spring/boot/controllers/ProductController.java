package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.ManufacturerDao;
import spring.boot.model.dao.ProductDao;
import spring.boot.services.ProductService;
import spring.boot.utils.CheckManufacturers;
import spring.boot.utils.CheckProducts;
import spring.boot.utils.GetManufacturerIdByName;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CheckProducts checkProducts;
    @Autowired
    GetManufacturerIdByName manufacturerId;
    @Autowired
    CheckManufacturers checkManufacturers;

    @GetMapping("/createProductForm")
    public ModelAndView createProductForm() {

        return new ModelAndView("products/createProductForm");
    }

    @PostMapping("/productCreated")
    public ModelAndView createProduct(@ModelAttribute("productName") String productName, @ModelAttribute("price") BigDecimal price,
                                      @ModelAttribute("manufacturerName") String manufacturerName, ProductDao product,
                                      ManufacturerDao manufacturer) {
        manufacturer.setId(manufacturerId.getManufacturerIdByName(manufacturerName));
        manufacturer.setName(manufacturerName);

        product.setName(productName);
        product.setPrice(price);
        product.setManufacturer(manufacturer);
        productService.create(product);

        return new ModelAndView("products/productCreated");
    }

    @GetMapping("/getProducts")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView("products/getProducts");
        mav.addObject("products", productService.getProducts());

        return mav;
    }

    @GetMapping("/deleteProductForm")
    public ModelAndView deleteProductForm() {

        return new ModelAndView("products/deleteProductForm");
    }

    @PostMapping("/productDeleted")
    public ModelAndView deleteManufacturer(@ModelAttribute("productName") String productName) {

        if (checkProducts.IsProductNameExists(productName)) {
            productService.deleteByName(productName);

            return new ModelAndView("products/productDeleted");
        }

        return new ModelAndView("products/productNameNotExists");
    }

    @GetMapping("/updateProductForm")
    public ModelAndView updateProductForm() {

        return new ModelAndView("products/updateProductForm");
    }

    @PostMapping("/productUpdated")
    public ModelAndView updateProduct(@ModelAttribute("newName") String newName, @ModelAttribute("newPrice") BigDecimal newPrice,
                                      @ModelAttribute("manufacturerName") String manufacturerName,
                                      @ModelAttribute("oldName") String oldName, ManufacturerDao manufacturer) {
        if (checkProducts.IsProductNameExists(oldName)) {
            if (checkManufacturers.IsManufacturerNameExists(manufacturerName)) {
                manufacturer.setId(manufacturerId.getManufacturerIdByName(manufacturerName));
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
