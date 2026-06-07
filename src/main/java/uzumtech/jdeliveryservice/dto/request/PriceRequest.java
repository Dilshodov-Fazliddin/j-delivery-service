package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.NotNull;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record PriceRequest(
        @NotNull(message = "Tariff type must not be null")
        TariffType tariffType,
        @NotNull(message = "Price must not be null")
        Double price,
        Boolean active
) {
}
