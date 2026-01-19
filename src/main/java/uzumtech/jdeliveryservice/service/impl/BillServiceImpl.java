package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uzumtech.jdeliveryservice.constant.Constant;
import uzumtech.jdeliveryservice.constant.enums.TariffTypeRule;
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
    public BillResponse calculateParcel(BillRequest request) {
        var tariffPrice = priceRepository.getByTariffType(request.tariffType());
        var rule = TariffTypeRule.valueOf(request.tariffType().name());

        int size = request.length() + request.width() + request.height();
        if (size > rule.getMaxSize() || request.weight() > rule.getMaxWeight()) {
            throw new IllegalArgumentException("Max size: " + rule.getMaxSize() +", max weight: " + rule.getMaxWeight());
        }

        double distance = Math.max(
                GeoUtils.distanceInKm(
                        request.latitudeFrom(),
                        request.longitudeFrom(),
                        request.latitudeTo(),
                        request.longitudeTo()) - Constant.FREE_DISTANCE,0);

        double finalPrice = Math.round(tariffPrice.getPrice() + distance * rule.getPricePerKm());

        return BillResponse.builder()
                .size(size)
                .tariffType(request.tariffType())
                .distance(Math.round(distance))
                .price(finalPrice)
                .build();
    }

}
