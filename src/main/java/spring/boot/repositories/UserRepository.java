package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.dao.UserDao;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserDao, UUID> {

}
