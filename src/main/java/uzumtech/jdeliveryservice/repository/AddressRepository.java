package uzumtech.jdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
