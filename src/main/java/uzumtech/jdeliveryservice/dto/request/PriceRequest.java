package uzumtech.jdeliveryservice.dto.request;

import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record PriceRequest(TariffType tariffType,
                            Double price
) {
}
