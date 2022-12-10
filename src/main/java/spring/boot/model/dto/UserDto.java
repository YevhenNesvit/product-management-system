package spring.boot.model.dto;

import spring.boot.model.dao.RoleDao;

import java.util.Set;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<RoleDao> roles;
}

