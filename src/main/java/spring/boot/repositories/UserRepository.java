package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.model.dao.UserDao;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDao, UUID> {

}
