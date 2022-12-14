package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.RoleDao;
import spring.boot.model.dao.UserDao;
import spring.boot.services.UserService;
import spring.boot.utils.CheckRoles;
import spring.boot.utils.CheckUsers;
import spring.boot.utils.GetRoleIdByName;

import javax.annotation.security.RolesAllowed;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final CheckUsers checkUsers;
    @Autowired
    private final GetRoleIdByName roleId;
    @Autowired
    private final CheckRoles checkRoles;

    @RolesAllowed("ADMIN")
    @GetMapping("/createUserForm")
    public ModelAndView createUserForm() {
        ModelAndView mav = new ModelAndView("users/createUserForm");
        mav.addObject("users", userService.getUsers());

        return mav;
    }

    @PostMapping("/userCreated")
    public ModelAndView createUser(@ModelAttribute("email") String email, @ModelAttribute("password") String password,
                                   @ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
                                   @ModelAttribute("roleName") String roleName, UserDao user, RoleDao role) {

        if (checkUsers.IsUserEmailExists(email)) {

            return new ModelAndView("users/userEmailAlreadyExists");
        } else if (checkRoles.IsRoleNameExists(roleName)) {
            role.setId(roleId.getRoleIdByName(roleName));
            role.setName(roleName);

            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(role);

            userService.create(user);

            return new ModelAndView("users/userCreated");
        } else {

            return new ModelAndView("roles/roleNameNotExists");
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/getUsers")
    public ModelAndView getUsers() {
        ModelAndView mav = new ModelAndView("users/getUsers");
        mav.addObject("users", userService.getUsers());

        return mav;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/deleteUserForm")
    public ModelAndView deleteUserForm() {

        return new ModelAndView("users/deleteUserForm");
    }

    @PostMapping("/userDeleted")
    public ModelAndView deleteUser(@ModelAttribute("email") String email) {

        if (checkUsers.IsUserEmailExists(email)) {
            userService.deleteByEmail(email);

            return new ModelAndView("users/userDeleted");
        }

        return new ModelAndView("users/userEmailNotExists");
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
        if (checkUsers.IsUserEmailExists(oldEmail)) {
            if (checkRoles.IsRoleNameExists(roleName)) {
                role.setId(roleId.getRoleIdByName(roleName));
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
