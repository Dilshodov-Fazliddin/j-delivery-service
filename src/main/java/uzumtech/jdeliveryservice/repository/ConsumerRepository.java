package uzumtech.jdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Long> {
}
