package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.repositories.UserRepository;
import spring.boot.services.SecurityService;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class IndexesController {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final SecurityService securityService;

    @GetMapping("")
    public ModelAndView getIndex() {

        return new ModelAndView("index");
    }

//    @GetMapping("/login")
//    public ModelAndView getLogin() {
//
//        return new ModelAndView("login");
//    }

    @GetMapping("/homepage")
    public ModelAndView getAdminHome() {
        Set<String> roles = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        for (int i = 0; i < roles.size(); i++) {
            if (roles.contains("ROLE_ADMIN")) {

                return new ModelAndView("adminhome");
            }
        }

        return new ModelAndView("userhome");
    }

}
