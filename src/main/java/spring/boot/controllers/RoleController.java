package spring.boot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.model.dto.RoleDto;
import spring.boot.services.RoleService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @RolesAllowed("ADMIN")
    @GetMapping("/createRoleForm")
    public ModelAndView createRoleForm() {
        ModelAndView mav = new ModelAndView("roles/createRoleForm");
        mav.addObject("RoleDto", new RoleDto());

        return mav;
    }

    @PostMapping("/createRoleForm")
    public ModelAndView createRole(@ModelAttribute("RoleDto") @Valid RoleDto role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("roles/createRoleForm");
        } else if (roleService.IsRoleNameExists(role.getName())) {

            return new ModelAndView("roles/roleNameAlreadyExists");
        } else {
            model.addAttribute("RoleDto", role);
            roleService.create(role);

            return new ModelAndView("roles/roleCreated");
        }
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
        ModelAndView mav = new ModelAndView("roles/updateRoleForm");
        mav.addObject("RoleDto", new RoleDto());

        return mav;
    }

    @PostMapping("/updateRoleForm")
    public ModelAndView updateRole(@ModelAttribute("RoleDto") @Valid RoleDto role, BindingResult bindingResult, Model model,
                                   @ModelAttribute("oldName") String oldName) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("roles/updateRoleForm");
        } else if (roleService.IsRoleNameExists(oldName)) {
            model.addAttribute("RoleDto", role);
            role.setId(roleService.getRoleIdByName(oldName));
            roleService.create(role);

            return new ModelAndView("roles/roleUpdated");
        } else {

            return new ModelAndView("roles/roleNameNotExists");
        }
    }
}
