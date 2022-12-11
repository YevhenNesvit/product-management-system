package spring.boot.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.services.RoleService;

@AllArgsConstructor
@Component
public class CheckRoles {
    @Autowired
    private final RoleService roleService;

    public boolean IsRoleNameExists(String name) {
        for (int i = 0; i < roleService.getRoles().size(); i++) {
            if(roleService.getRoles().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
