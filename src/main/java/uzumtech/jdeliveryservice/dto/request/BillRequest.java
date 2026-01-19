package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record BillRequest(
        @NotNull(message = "Length is blank, please fill this field")
        int length,
        @NotNull(message = "Width is blank, please fill this field")
        int width,
        @NotNull(message = "Height is blank, please fill this field")
        int height,
        @NotNull(message = "Weight is blank, please fill this field")
        int weight,
        @NotNull(message = "Tariff type is blank, please fill this field")
        TariffType tariffType,
        @NotNull(message = "Latitude from is blank, please fill this field")
        double latitudeFrom,
        @NotNull(message = "Longitude from is blank, please fill this field")
        double longitudeFrom,
        @NotNull(message = "Latitude to is blank, please fill this field")
        double latitudeTo,
        @NotNull(message = "Longitude to is blank, please fill this field")
        double longitudeTo
) {}
