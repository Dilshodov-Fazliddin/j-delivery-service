package uzumtech.jdeliveryservice.dto.request;


import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record ParcelRequest(
        Integer length,
        Integer width,
        Integer height,
        Double weight,
        String description,
        ParcelStatus parcelStatus,
        TariffType tariffType,
        Long merchantId,
        Long consumerId
) {}
