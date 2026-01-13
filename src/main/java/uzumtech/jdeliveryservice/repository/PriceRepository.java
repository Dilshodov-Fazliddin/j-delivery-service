package uzumtech.jdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
