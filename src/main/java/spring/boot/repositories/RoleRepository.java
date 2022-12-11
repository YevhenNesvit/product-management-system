package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.model.dao.RoleDao;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Role WHERE name = :name")
    void deleteByName(@Param("name") String name);
}
