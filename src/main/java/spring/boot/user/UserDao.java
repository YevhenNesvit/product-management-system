package spring.boot.user;

import lombok.AllArgsConstructor;
import spring.boot.role.RoleDao;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class UserDao {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleDao role;

    public UserDao() {

    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public RoleDao getRole() {
        return role;
    }

    public void setRole(RoleDao role) {
        this.role = role;
    }
}
