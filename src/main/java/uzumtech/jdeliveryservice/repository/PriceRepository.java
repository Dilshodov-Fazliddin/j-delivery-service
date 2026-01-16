package uzumtech.jdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uzumtech.jdeliveryservice.constant.enums.TariffType;
import uzumtech.jdeliveryservice.entity.PriceEntity;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    PriceEntity getByTariffType(TariffType tariffType);

    boolean existsByTariffType(TariffType tariffType);

    Page<PriceEntity> searchAllByActiveTrue(Pageable pageable);
    Page<PriceEntity> searchAllByActiveFalse(Pageable pageable);
}
