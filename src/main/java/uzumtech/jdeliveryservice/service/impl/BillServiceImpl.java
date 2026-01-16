package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uzumtech.jdeliveryservice.constant.enums.TariffType;
import uzumtech.jdeliveryservice.dto.request.BillRequest;
import uzumtech.jdeliveryservice.dto.response.BillResponse;
import uzumtech.jdeliveryservice.repository.PriceRepository;
import uzumtech.jdeliveryservice.service.BillService;
import uzumtech.jdeliveryservice.utils.GeoUtils;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BillServiceImpl implements BillService {

    PriceRepository priceRepository;

    @Override
    public BillResponse calculateParcel(BillRequest billRequest) {

        var tariffPrice = priceRepository.getByTariffType(billRequest.tariffType());
        int maxSize = billRequest.length() + billRequest.width() + billRequest.height();
        double distance = GeoUtils.distanceInKm(
                billRequest.latitudeFrom(),
                billRequest.longitudeFrom(),
                billRequest.latitudeTo(),
                billRequest.longitudeTo()
        ) - 5.0;

        switch (tariffPrice.getTariffType()) {
            case START -> {
                if (maxSize <= 150 && billRequest.weight() <= 20) {
                    double distancePrice = distance * 1000;
                    double finalPrice =Math.round(tariffPrice.getPrice() + distancePrice);

                    return BillResponse.builder()
                            .size(maxSize)
                            .tariffType(TariffType.START)
                            .distance(distance)
                            .price(finalPrice)
                            .build();
                } else {
                    throw new IllegalArgumentException(
                            "Max size is 150 and weight limit is 20. Your max size: " + maxSize +
                                    ", weight: " + billRequest.weight()
                    );
                }
            }
            case ECONOMY -> {
                if (maxSize <= 450 && billRequest.weight() <= 40) {
                    double distancePrice = distance * 1200;
                    double finalPrice =Math.round(tariffPrice.getPrice() + distancePrice);


                    return BillResponse.builder()
                            .size(maxSize)
                            .tariffType(TariffType.ECONOMY)
                            .distance(distance)
                            .price(finalPrice)
                            .build();
                } else {
                    throw new IllegalArgumentException(
                            "Max size is 450 and weight limit is 40. Your max size: " + maxSize +
                                    ", weight: " + billRequest.weight()
                    );
                }
            }
            case COMFORT -> {
                if (maxSize <= 750 && billRequest.weight() <= 60) {
                    double distancePrice = distance * 1500;
                    double finalPrice =Math.round(tariffPrice.getPrice() + distancePrice);

                    return BillResponse.builder()
                            .size(maxSize)
                            .tariffType(TariffType.COMFORT)
                            .distance(distance)
                            .price(finalPrice)
                            .build();
                } else {
                    throw new IllegalArgumentException(
                            "Max size is 750 and weight limit is 60. Your max size: " + maxSize +
                                    ", weight: " + billRequest.weight()
                    );
                }
            }
            case PRIORITY -> {
                if (maxSize <= 1050 && billRequest.weight() <= 70) {
                    double distancePrice = distance * 2000;
                    double finalPrice =Math.round(tariffPrice.getPrice() + distancePrice);


                    return BillResponse.builder()
                            .size(maxSize)
                            .tariffType(TariffType.PRIORITY)
                            .distance(distance)
                            .price(finalPrice)
                            .build();
                } else {
                    throw new IllegalArgumentException(
                            "Max size is 1050 and weight limit is 70. Your max size: " + maxSize +
                                    ", weight: " + billRequest.weight()
                    );
                }
            }
            default -> throw new IllegalArgumentException("Something is wrong, may be the tariff which you chose may not exist in our system");
        }
    }
}
