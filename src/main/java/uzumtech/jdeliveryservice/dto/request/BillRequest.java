package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record BillRequest(
        @NotNull(message = "Length must not be null")
        @Positive(message = "Length must be positive")
        Integer length,

        @NotNull(message = "Width must not be null")
        @Positive(message = "Width must be positive")
        Integer width,

        @NotNull(message = "Height must not be null")
        @Positive(message = "Height must be positive")
        Integer height,

        @NotNull(message = "Weight must not be null")
        @Positive(message = "Weight must be positive")
        Double weight,

        @NotNull(message = "Tariff type must not be null")
        TariffType tariffType,

        @NotNull(message = "Latitude (from) must not be null")
        Double latitudeFrom,

        @NotNull(message = "Longitude (from) must not be null")
        Double longitudeFrom,

        @NotNull(message = "Latitude (to) must not be null")
        Double latitudeTo,

        @NotNull(message = "Longitude (to) must not be null")
        Double longitudeTo
) {
}
