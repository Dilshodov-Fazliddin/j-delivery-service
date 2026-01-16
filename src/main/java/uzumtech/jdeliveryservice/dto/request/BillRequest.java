package uzumtech.jdeliveryservice.dto.request;

import uzumtech.jdeliveryservice.constant.enums.TariffType;

public record BillRequest(
        int length,
        int width,
        int height,
        int weight,
        TariffType tariffType,
        double latitudeFrom,
        double longitudeFrom,
        double latitudeTo,
        double longitudeTo
) {}
