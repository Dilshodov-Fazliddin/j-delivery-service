package uzumtech.jdeliveryservice.dto.response;

import lombok.Builder;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

@Builder
public record BillResponse(
        int size,
        TariffType tariffType,
        double distance,
        double price
) {}