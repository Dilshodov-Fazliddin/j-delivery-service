package uzumtech.jdeliveryservice.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import uzumtech.jdeliveryservice.entity.AddressEntity;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<AddressEntity> getAddressByConsumerId(Long id);

    List<AddressEntity> findByConsumer(ConsumerEntity consumer);

    Optional<AddressEntity> findByConsumer_Id(Long consumerId);
}
