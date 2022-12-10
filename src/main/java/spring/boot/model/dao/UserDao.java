package spring.boot.model.dao;

import java.util.Set;
import java.util.UUID;

public class UserDao {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<RoleDao> roles;
}
