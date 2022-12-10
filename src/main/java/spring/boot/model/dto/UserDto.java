package spring.boot.model.dto;

import lombok.AllArgsConstructor;
import spring.boot.model.dao.RoleDao;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<RoleDao> roles;

    public UserDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<RoleDao> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDao> roles) {
        this.roles = roles;
    }
}

