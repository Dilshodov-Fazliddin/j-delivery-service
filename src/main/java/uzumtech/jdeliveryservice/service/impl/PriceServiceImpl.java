package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.jdeliveryservice.dto.request.PriceRequest;
import uzumtech.jdeliveryservice.dto.response.PriceResponse;
import uzumtech.jdeliveryservice.entity.PriceEntity;
import uzumtech.jdeliveryservice.exception.DataNotFoundException;
import uzumtech.jdeliveryservice.mapper.PriceMapper;
import uzumtech.jdeliveryservice.repository.PriceRepository;
import uzumtech.jdeliveryservice.service.PriceService;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@Slf4j
public class PriceServiceImpl implements PriceService {

    PriceRepository priceRepository;
    PriceMapper priceMapper;

    @Override
    public PriceResponse create(PriceRequest priceRequest) {
        if (priceRepository.existsByTariffType(priceRequest.tariffType())) {
            throw new IllegalStateException("Price already exists for tariff: " + priceRequest.tariffType() +" You should update Tariff plan");
        }
        var newPrice = priceMapper.toEntity(priceRequest);
        var saved = priceRepository.save(newPrice);

        log.info("Saved price with id:{}", saved.getId());
        return priceMapper.toResponse(saved);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateById(Long id, PriceRequest priceRequest) {
        var priceEntity = priceRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Price not found with id: " + id));

        priceMapper.updatePriceFromDto(priceRequest,priceEntity);

        log.info("Updated price with id:{}", id);
    }

    @Override
    @Transactional
    public void deletePriceById(Long id) {
        var priceEntity = priceRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Price not found with id: " + id));
        priceEntity.setActive(false);

        log.info("Deleted price with id:{}", id);
    }

    @Override
    public Page<PriceResponse> findAllByPage(Pageable pageable) {
        Page<PriceEntity> priceEntities = priceRepository.searchAllByActiveTrue(pageable);
        return priceEntities.map(priceMapper::toResponse);
    }

    @Override
    public Page<PriceResponse> getDeletedPrices(Pageable pageable) {
        Page<PriceEntity> priceEntities = priceRepository.searchAllByActiveFalse(pageable);
        return priceEntities.map(priceMapper::toResponse);
    }
}
