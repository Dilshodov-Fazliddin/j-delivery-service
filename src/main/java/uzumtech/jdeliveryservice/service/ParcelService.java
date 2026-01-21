package uzumtech.jdeliveryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.request.ParcelUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.ParcelResponse;

public interface ParcelService {
    ParcelResponse createParcel(Long id,ParcelRequest parcelRequest);
    void deleteParcelById(Long id);
    void updateParcelById(Long id, ParcelUpdateRequest parcelRequest);
    Page<ParcelResponse> getActiveParcelById(Long id, Pageable pageable);
    void updateStatusOfParcel(Long id, ParcelStatus parcelStatus);
}
