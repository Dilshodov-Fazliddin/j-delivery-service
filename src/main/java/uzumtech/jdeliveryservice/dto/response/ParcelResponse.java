package uzumtech.jdeliveryservice.dto.response;

import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record ParcelResponse(
        Long id,
        Integer length,
        Integer width,
        Integer height,
        Double weight,
        String description,
        ParcelStatus parcelStatus,
        TariffType tariffType,
        double latitudeFrom,
        double longitudeFrom,
        double latitudeTo,
        double longitudeTo
) {}
