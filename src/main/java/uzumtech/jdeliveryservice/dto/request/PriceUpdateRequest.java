package uzumtech.jdeliveryservice.dto.request;

import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record PriceUpdateRequest(
        TariffType tariffType,
        Double price,
        Boolean active
) {
}
