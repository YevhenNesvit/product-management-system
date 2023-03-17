package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.converter.RoleConverter;
import spring.boot.model.dao.RoleDao;
import spring.boot.model.dto.UserDto;
import spring.boot.services.RoleService;
import spring.boot.services.UserService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleConverter converter;

    @GetMapping("/createUserForm")
    public ModelAndView createUserForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("UserDto", new UserDto());

        return mav;
    }

    @PostMapping("/createUserForm")
    public ModelAndView createUser(@ModelAttribute("UserDto") @Valid UserDto user, BindingResult bindingResult,
                                   @ModelAttribute("roleName") String roleName, Model model, RoleDao role) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("users/createUserForm");
        } else if (userService.IsUserEmailExists(user.getEmail())) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("userExists", "User already exists!");

            return mav;
        } else if (roleService.IsRoleNameExists(roleName)) {
            model.addAttribute("UserDto", user);

            role.setId(roleService.getRoleIdByName(roleName));
            role.setName(roleName);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(converter.from(role));

            userService.create(user);

            return new ModelAndView("users/userCreated");
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("roleNotExists", "Role does not exist!");

            return mav;
        }
    }

    @GetMapping("/getUsers")
    public ModelAndView getUsers() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.getUsers());

        return mav;
    }

    @GetMapping("/deleteUserForm")
    public ModelAndView deleteUserForm() {

        return new ModelAndView("users/deleteUserForm");
    }

    @PostMapping("/deleteUserForm")
    public ModelAndView deleteUser(@ModelAttribute("email") String email) {

        if (userService.IsUserEmailExists(email)) {
            userService.deleteByEmail(email);

            return new ModelAndView("users/userDeleted");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("userDoesNotExists", "User does not exist!");

        return mav;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/updateUserForm")
    public ModelAndView updateUserForm() {

        return new ModelAndView("users/updateUserForm");
    }

    @PostMapping("/userUpdated")
    public ModelAndView updateProduct(@ModelAttribute("newEmail") String newEmail, @ModelAttribute("password") String password,
                                      @ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
                                      @ModelAttribute("roleName") String roleName, @ModelAttribute("oldEmail") String oldEmail,
                                      RoleDao role) {
        if (userService.IsUserEmailExists(oldEmail)) {
            if (roleService.IsRoleNameExists(roleName)) {
                role.setId(roleService.getRoleIdByName(roleName));
                role.setName(roleName);
                userService.updateByEmail(newEmail, password, firstName, lastName, role, oldEmail);

                return new ModelAndView("users/userUpdated");
            } else {

                return new ModelAndView("roles/roleNameNotExists");
            }
        } else {

            return new ModelAndView("users/userEmailNotExists");
        }
    }
}
