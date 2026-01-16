package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record PriceRequest(
        @NotNull(message = "Tariff type is blank, please fill this field")
        TariffType tariffType,
        @NotNull(message = "price is blank, please fill this field")
        Double price,
        Boolean active
) {
}
