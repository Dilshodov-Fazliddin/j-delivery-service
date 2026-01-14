package uzumtech.jdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.MerchantEntity;

public interface MerchantRepository extends JpaRepository<MerchantEntity,Long> {
}
