package spring.boot.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.role.RoleConverter;
import spring.boot.role.RoleDao;
import spring.boot.user.UserDao;
import spring.boot.user.UserDto;
import spring.boot.user.UserRepository;
import spring.boot.role.RoleService;
import spring.boot.user.UserService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
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

    @GetMapping("/homepage")
    public ModelAndView getHomePage() {

        return new ModelAndView("homepage");
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("UserDto", new UserDto());

        return mav;
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@ModelAttribute("UserDto") @Valid UserDto user, BindingResult bindingResult,
                                   @ModelAttribute("confirm") String confirm, Model model, RoleDao role) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("registration");
        } else if (!user.getPassword().equals(confirm)) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("confirmation", "Passwords do not match!");

            return mav;
        } else if (userService.IsUserEmailExists(user.getEmail())) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("userExists", "User already exists!");

            return mav;
        } else {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("success", "User successfully registered!");
            model.addAttribute("UserDto", user);

            role.setId(roleService.getRoleIdByName("ROLE_USER"));
            role.setName("ROLE_USER");

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(converter.from(role));

            userService.create(user);

            return mav;
        }
    }

    @ModelAttribute("userName")
    public UserDao globalUserObject(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDao user = new UserDao();
        List<UserDao> users = repository.findAll();
        for (UserDao userDao : users) {
            if (userDao.getEmail().equals(authentication.getName())) {
                user = userDao;
            }
        }
        model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());

        return user;
    }
}
