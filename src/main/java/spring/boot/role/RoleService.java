package spring.boot.role;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.RoleConverter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RoleService {
    @Autowired
    private final RoleRepository repository;
    @Autowired
    private final RoleConverter converter;

    public void create(RoleDto role) {
        repository.save(converter.to(role));
    }

    public List<RoleDto> getRoles() {
        return converter.fromList(repository.findAll());
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public boolean IsRoleNameExists(String name) {
        for (int i = 0; i < getRoles().size(); i++) {
            if(getRoles().get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public UUID getRoleIdByName(String name) {
        List<RoleDto> roles = getRoles();
        UUID id = null;
        for (RoleDto role : roles) {
            if (role.getName().equals(name)) {
                id = role.getId();
            }
        }
        return id;
    }
}
