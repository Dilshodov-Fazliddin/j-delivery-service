package uzumtech.jdeliveryservice.dto.request;


import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record ParcelRequest(
        Integer length,
        Integer width,
        Integer height,
        Double weight,
        String description,
        TariffType tariffType,
        Long merchantId,
        Boolean active,
        ParcelStatus parcelStatus
) {}
