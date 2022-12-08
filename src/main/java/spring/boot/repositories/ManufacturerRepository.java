package spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.model.dao.ManufacturerDao;

import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerDao, UUID> {
}
