package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dao.RoleDao;
import spring.boot.services.RoleService;

import javax.annotation.security.RolesAllowed;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @RolesAllowed("ADMIN")
    @GetMapping("/createRoleForm")
    public ModelAndView createRoleForm() {

        return new ModelAndView("roles/createRoleForm");
    }

    @PostMapping("/roleCreated")
    public ModelAndView createRole(@ModelAttribute("roleName") String roleName, RoleDao role) {
        if (roleService.IsRoleNameExists(roleName)) {

            return new ModelAndView("roles/roleNameAlreadyExists");
        }

        role.setName(roleName);
        roleService.create(role);

        return new ModelAndView("roles/roleCreated");
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/getRoles")
    public ModelAndView getRoles() {
        ModelAndView mav = new ModelAndView("roles/getRoles");
        mav.addObject("roles", roleService.getRoles());

        return mav;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/deleteRoleForm")
    public ModelAndView deleteRoleForm() {

        return new ModelAndView("roles/deleteRoleForm");
    }

    @PostMapping("/roleDeleted")
    public ModelAndView deleteRole(@ModelAttribute("roleName") String roleName) {

        if (roleService.IsRoleNameExists(roleName)) {
            roleService.deleteByName(roleName);

            return new ModelAndView("roles/roleDeleted");
        }

        return new ModelAndView("roles/roleNameNotExists");
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/updateRoleForm")
    public ModelAndView updateRoleForm() {

        return new ModelAndView("roles/updateRoleForm");
    }

    @PostMapping("/roleUpdated")
    public ModelAndView updateRole(@ModelAttribute("newName") String newName, @ModelAttribute("oldName") String oldName) {

        if (roleService.IsRoleNameExists(oldName)) {
            roleService.updateByName(newName, oldName);

            return new ModelAndView("roles/roleUpdated");
        }

        return new ModelAndView("roles/roleNameNotExists");
    }
}
