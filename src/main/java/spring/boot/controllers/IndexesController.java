package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class IndexesController {

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
    public ModelAndView getHomePage() {
        Set<String> roles = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        System.out.println(roles);
        for (int i = 0; i < roles.size(); i++) {
            if (roles.contains("ROLE_ADMIN")) {

                return new ModelAndView("adminhome");
            }
        }

        return new ModelAndView("userhome");
    }

}
