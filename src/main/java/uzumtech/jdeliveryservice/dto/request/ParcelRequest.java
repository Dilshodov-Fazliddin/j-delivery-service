package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.*;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record ParcelRequest(

        @NotNull(message = "Length must not be null")
        Integer length,

        @NotNull(message = "Width must not be null")
        Integer width,

        @NotNull(message = "Height must not be null")
        Integer height,

        @NotNull(message = "Weight must not be null")
        Double weight,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotNull(message = "Tariff type must not be null")
        TariffType tariffType,

        @NotNull(message = "Merchant id must not be null")
        Long merchantId,

        @NotNull(message = "Active flag must not be null")
        Boolean active,

        ParcelStatus parcelStatus,

        @NotNull(message = "Latitude (from) must not be null")
        Double latitudeFrom,

        @NotNull(message = "Longitude (from) must not be null")
        Double longitudeFrom,

        @NotNull(message = "Latitude (to) must not be null")
        Double latitudeTo,

        @NotNull(message = "Longitude (to) must not be null")
        Double longitudeTo,

        Double price,

        Double distance
) {}
