package spring.boot.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerDao, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Manufacturer WHERE name = :name")
    void deleteByName(@Param("name") String name);
}
