package spring.boot.role;

import lombok.AllArgsConstructor;
import spring.boot.user.UserDto;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class RoleDto {
    private UUID id;
    @NotBlank
    private String name;
    private Set<UserDto> users;

    public RoleDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
