package spring.boot.model.dao;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Entity(name = "Role")
@Table(name = "roles")
public class RoleDao {
    private UUID id;
    private String name;
    private Set<UserDao> users;

    public RoleDao() {

    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "role_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role")
    public Set<UserDao> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDao> users) {
        this.users = users;
    }
}
