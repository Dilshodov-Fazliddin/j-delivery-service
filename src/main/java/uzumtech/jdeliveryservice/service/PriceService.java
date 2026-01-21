package uzumtech.jdeliveryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uzumtech.jdeliveryservice.dto.request.PriceRequest;
import uzumtech.jdeliveryservice.dto.request.PriceUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.PriceResponse;

public interface PriceService {
    PriceResponse create(PriceRequest priceRequest);
    void updateById(Long id, PriceUpdateRequest priceRequest);
    void deletePriceById(Long id);
    Page<PriceResponse> findAllByPage(Pageable pageable);
    Page<PriceResponse> getDeletedPrices(Pageable pageable);
}
