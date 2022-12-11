package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.RoleDao;
import spring.boot.services.RoleService;
import spring.boot.utils.CheckRoles;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final CheckRoles checkRoles;

    @GetMapping("/createRoleForm")
    public ModelAndView createRoleForm() {

        return new ModelAndView("roles/createRoleForm");
    }

    @PostMapping("/roleCreated")
    public ModelAndView createRole(@ModelAttribute("roleName") String roleName, RoleDao role) {
        if (checkRoles.IsRoleNameExists(roleName)) {

            return new ModelAndView("roles/roleNameAlreadyExists");
        }

        role.setName(roleName);
        roleService.create(role);

        return new ModelAndView("roles/roleCreated");
    }

    @GetMapping("/getRoles")
    public ModelAndView getRoles() {
        ModelAndView mav = new ModelAndView("roles/getRoles");
        mav.addObject("roles", roleService.getRoles());

        return mav;
    }

    @GetMapping("/deleteRoleForm")
    public ModelAndView deleteRoleForm() {

        return new ModelAndView("roles/deleteRoleForm");
    }

    @PostMapping("/roleDeleted")
    public ModelAndView deleteRole(@ModelAttribute("roleName") String roleName) {

        if (checkRoles.IsRoleNameExists(roleName)) {
            roleService.deleteByName(roleName);

            return new ModelAndView("roles/roleDeleted");
        }

        return new ModelAndView("roles/roleNameNotExists");
    }
}
