package spring.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.model.dto.RoleDto;
import spring.boot.services.RoleService;

import java.util.List;
import java.util.UUID;

@Component
public class GetRoleIdByName {
    @Autowired
    RoleService roleService;

    public UUID getRoleIdByName(String name) {
        List<RoleDto> roles = roleService.getRoles();
        UUID id = null;
        for (RoleDto role : roles) {
            if (role.getName().equals(name)) {
                id = role.getId();
            }
        }
        return id;
    }
}
