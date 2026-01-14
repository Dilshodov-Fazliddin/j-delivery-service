package uzumtech.jdeliveryservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.response.ParcelResponse;
import uzumtech.jdeliveryservice.entity.ParcelEntity;
import uzumtech.jdeliveryservice.service.ParcelService;

@RestController
@RequestMapping("/api/delivery/parcels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ParcelController {

    ParcelService parcelService;

    @PostMapping("/{consumerId}")
    public ParcelResponse createParcel(
            @PathVariable Long consumerId,
            @RequestBody @Valid ParcelRequest parcelRequest
    ) {
        return parcelService.createParcel(consumerId, parcelRequest);
    }


    @PatchMapping("/{parcelId}")
    public void updateParcel(
            @PathVariable Long parcelId,
            @RequestBody @Valid ParcelRequest parcelRequest
    ) {
        parcelService.updateParcelById(parcelId, parcelRequest);
    }

    @DeleteMapping("/{parcelId}")
    public void deleteParcel(
            @PathVariable Long parcelId
    ) {
        parcelService.deleteParcelById(parcelId);
    }

    @GetMapping("/consumer/{consumerId}")
    public Page<ParcelResponse> getActiveParcels(
            @PathVariable Long consumerId,
            Pageable pageable
    ) {
        return parcelService.getActiveParcelById(consumerId, pageable);
    }
}

