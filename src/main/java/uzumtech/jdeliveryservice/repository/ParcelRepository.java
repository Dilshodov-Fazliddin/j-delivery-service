package uzumtech.jdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.ParcelEntity;

public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
}
