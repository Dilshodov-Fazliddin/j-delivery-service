package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record ParcelUpdateRequest(
        Integer length,

        Integer width,

        Integer height,

        Double weight,

        String description,

        TariffType tariffType,

        Long merchantId,

        Boolean active,

        ParcelStatus parcelStatus,

        Double latitudeFrom,

        Double longitudeFrom,

        Double latitudeTo,

        Double longitudeTo,

        Double price,

        Double distance
) {
}
