package uzumtech.jdeliveryservice.dto.response;

import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record PriceResponse(
                             TariffType tariffType,
                             Double price
) {
}
