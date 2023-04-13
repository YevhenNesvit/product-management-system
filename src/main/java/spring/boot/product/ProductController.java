package spring.boot.product;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.boot.manufacturer.ManufacturerConverter;
import spring.boot.manufacturer.ManufacturerDao;
import spring.boot.manufacturer.ManufacturerService;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ManufacturerService manufacturerService;
    @Autowired
    private final ManufacturerConverter converter;

    @GetMapping("/createProductForm")
    public ModelAndView createProductForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("manufacturers", manufacturerService.getManufacturers());
        mav.addObject("ProductDto", new ProductDto());

        return mav;
    }

    @PostMapping("/createProductForm")
    public ModelAndView createProduct(@ModelAttribute("ProductDto") @Valid ProductDto product, BindingResult bindingResult,
                                      @ModelAttribute("manufacturerName") String manufacturerName, Model model,
                                      ManufacturerDao manufacturer, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("products/createProductForm");
        } else if (productService.IsProductNameExists(product.getName())) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("productExists", "Product already exists!");

            return mav;
        } else if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
            ModelAndView mav = new ModelAndView("redirect:/products/getProducts");
            redirect.addFlashAttribute("creation", "Product successfully created!");

            model.addAttribute("ProductDto", product);

            manufacturer.setId(manufacturerService.getManufacturerIdByName(manufacturerName));
            manufacturer.setName(manufacturerName);

            product.setManufacturer(converter.from(manufacturer));

            productService.create(product);

            return mav;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("manufacturerNotExists", "Manufacturer does not exist!");

            return mav;
        }
    }

    @GetMapping("/getProducts")
    public ModelAndView getManufacturers() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("products", productService.getProducts());

        return mav;
    }

    @GetMapping("/deleteProductForm")
    public ModelAndView deleteProductForm() {

        return new ModelAndView("products/deleteProductForm");
    }

    @PostMapping("/deleteProductForm")
    public ModelAndView deleteManufacturer(@ModelAttribute("productName") String productName, RedirectAttributes redirect) {

        if (productService.IsProductNameExists(productName)) {
            ModelAndView mav = new ModelAndView("redirect:/products/getProducts");
            redirect.addFlashAttribute("deletion", "Product successfully deleted!");

            productService.deleteByName(productName);

            return mav;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("productDoesNotExists", "Product does not exist!");

        return mav;
    }

    @GetMapping("/updateProductForm")
    public ModelAndView updateProductForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("ProductDto", new ProductDto());

        return mav;
    }

    @PostMapping("/updateProductForm")
    public ModelAndView updateProduct(@ModelAttribute("ProductDto") @Valid ProductDto product, BindingResult bindingResult,
                                      @ModelAttribute("oldName") String oldName, Model model, ManufacturerDao manufacturer,
                                      @ModelAttribute("manufacturerName") String manufacturerName) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("products/updateProductForm");
        } else if (productService.IsProductNameExists(oldName)) {
            if (manufacturerService.IsManufacturerNameExists(manufacturerName)) {
                model.addAttribute("productDto", product);

                manufacturer.setId(manufacturerService.getManufacturerIdByName(manufacturerName));
                manufacturer.setName(manufacturerName);

                product.setId(productService.getProductIdByName(oldName));
                product.setManufacturer(converter.from(manufacturer));

                productService.create(product);

                return new ModelAndView("products/productUpdated");
            } else {
                ModelAndView mav = new ModelAndView();
                mav.addObject("manufacturerDoesNotExists", "Manufacturer does not exist!");

                return mav;
            }
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("productDoesNotExists", "Product does not exist!");

            return mav;
        }
    }
}
