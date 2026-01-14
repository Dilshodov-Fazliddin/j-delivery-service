package uzumtech.jdeliveryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.response.ParcelResponse;
import uzumtech.jdeliveryservice.entity.ParcelEntity;

public interface ParcelService {
    ParcelResponse createParcel(Long id,ParcelRequest parcelRequest);
    void deleteParcelById(Long id);
    void updateParcelById(Long id, ParcelRequest parcelRequest);
    Page<ParcelEntity> getActiveParcelById(Long id, Pageable pageable);
}
