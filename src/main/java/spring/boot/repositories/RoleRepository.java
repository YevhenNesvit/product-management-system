package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.model.dao.RoleDao;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleDao, UUID> {

}
