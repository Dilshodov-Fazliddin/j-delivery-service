package uzumtech.jdeliveryservice.dto.response;

import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record PriceResponse(Long id,
                             TariffType tariffType,
                             Double price
) {
}
