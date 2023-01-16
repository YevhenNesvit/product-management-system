package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.converter.RoleConverter;
import spring.boot.model.dao.RoleDao;
import spring.boot.model.dto.UserDto;
import spring.boot.services.RoleService;
import spring.boot.services.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class IndexesController {
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleConverter converter;

    @GetMapping("")
    public ModelAndView getIndex() {

        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {

        return new ModelAndView("login");
    }

    @GetMapping("/homepage")
    public ModelAndView getHomePage() {

        return new ModelAndView("homepage");
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationForm() {
        ModelAndView mav = new ModelAndView("registration");
        mav.addObject("UserDto", new UserDto());

        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@ModelAttribute("UserDto") @Valid UserDto user, BindingResult bindingResult,
                                   @ModelAttribute("confirm") String confirm, Model model, RoleDao role) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("registration");
        } else if (userService.IsUserEmailExists(user.getEmail())) {

            return new ModelAndView("users/userAlreadyExists");
        } else if (!user.getPassword().equals(confirm)) {

            return new ModelAndView("/passwordValidation");
        } else {
            model.addAttribute("UserDto", user);
            role.setId(roleService.getRoleIdByName("ROLE_USER"));
            role.setName("ROLE_USER");

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(converter.from(role));

            userService.create(user);

            return new ModelAndView("users/userRegistered");
        }
    }
}
