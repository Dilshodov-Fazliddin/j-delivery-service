package uzumtech.jdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.ParcelEntity;

import java.util.Optional;

public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    Page<ParcelEntity> findByActiveTrueAndConsumerId(Long consumerId, Pageable pageable);
}
