package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.dao.RoleDao;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, UUID> {

}
