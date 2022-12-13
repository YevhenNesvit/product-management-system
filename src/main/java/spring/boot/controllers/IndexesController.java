package spring.boot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("")
public class IndexesController {

    @GetMapping("/admin")
    public ModelAndView getAdminHome() {

        return new ModelAndView("adminhome");
    }

    @GetMapping("/user")
    public ModelAndView getUserHome() {

        return new ModelAndView("userhome");
    }

}
