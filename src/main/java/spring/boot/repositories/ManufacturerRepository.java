package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.model.dao.ManufacturerDao;

import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerDao, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Manufacturer WHERE name = :name")
    void deleteByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE Manufacturer SET name = :newName WHERE name = :oldName")
    void updateByName(@Param("newName") String newName, @Param("oldName") String oldName);
}
