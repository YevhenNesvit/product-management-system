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

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @GetMapping("/createRoleForm")
    public ModelAndView createRoleForm() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("RoleDto", new RoleDto());

        return mav;
    }

    @PostMapping("/createRoleForm")
    public ModelAndView createRole(@ModelAttribute("RoleDto") @Valid RoleDto role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return new ModelAndView("roles/createRoleForm");
        } else if (roleService.IsRoleNameExists(role.getName())) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("roleExists", "Role already exists!");

            return mav;
        } else {
            model.addAttribute("RoleDto", role);
            roleService.create(role);

            return new ModelAndView("roles/roleCreated");
        }
    }

    @GetMapping("/getRoles")
    public ModelAndView getRoles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles", roleService.getRoles());

        return mav;
    }

    @GetMapping("/deleteRoleForm")
    public ModelAndView deleteRoleForm() {

        return new ModelAndView("roles/deleteRoleForm");
    }

    @PostMapping("/deleteRoleForm")
    public ModelAndView deleteRole(@ModelAttribute("roleName") String roleName) {

        if (roleService.IsRoleNameExists(roleName)) {
            roleService.deleteByName(roleName);

            return new ModelAndView("roles/roleDeleted");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("roleDoesNotExists", "Role does not exist!");

        return mav;
    }

    @GetMapping("/updateRoleForm")
    public ModelAndView updateRoleForm() {
        ModelAndView mav = new ModelAndView();
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
            ModelAndView mav = new ModelAndView();
            mav.addObject("roleDoesNotExists", "Role does not exist!");

            return mav;
        }
    }
}
