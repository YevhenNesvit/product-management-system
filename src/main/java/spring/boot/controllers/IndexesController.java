package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.repositories.UserRepository;
import spring.boot.services.SecurityService;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class IndexesController {

    @Autowired
    private final UserRepository repository;

    @GetMapping("")
    public ModelAndView getIndex() {

        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {

        return new ModelAndView("login");
    }

    @PostMapping("/userhome")
    public ModelAndView getAdminHome(@ModelAttribute("username") String username) {
        for (int i = 0; i < repository.findByEmail(username).size(); i++) {
            if (repository.findByEmail(username).get(i).getRole().getName().equals("ROLE_ADMIN")) {

                return new ModelAndView("adminhome");
            }
        }

        return new ModelAndView("userhome");
    }

}
