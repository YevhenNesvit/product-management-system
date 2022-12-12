package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.model.dao.RoleDao;
import spring.boot.model.dao.UserDao;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDao, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM User WHERE email = :email")
    void deleteByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User SET email = :newEmail, password = :password, firstName = :firstName, lastName = :lastName," +
            "role = :role WHERE email = :oldEmail")
    void updateByEmail(@Param("newEmail") String newEmail, @Param("password") String password, @Param("firstName")
    String firstName, @Param("lastName") String lastName, @Param("role") RoleDao role, @Param("oldEmail") String oldEmail);
}
