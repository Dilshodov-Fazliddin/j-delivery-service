package uzumtech.jdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.MerchantEntity;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantEntity,Long> {
    Optional<MerchantEntity> findByName(String name);
}
