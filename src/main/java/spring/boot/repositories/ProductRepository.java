package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.model.dao.ProductDao;
import spring.boot.model.dto.ManufacturerDto;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductDao, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Product WHERE name = :name")
    void deleteByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE Product SET name = :newName, price = :newPrice, manufacturer = :newManufacturer WHERE name = :oldName")
    void updateByName(@Param("newName") String newName, @Param("newPrice") BigDecimal newPrice, @Param("newManufacturer")
    ManufacturerDto newManufacturer, @Param("oldName") String oldName);
}
