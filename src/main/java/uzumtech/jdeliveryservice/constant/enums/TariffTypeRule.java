package uzumtech.jdeliveryservice.constant.enums;

import lombok.Getter;

@Getter
public enum TariffTypeRule {
    START(150, 20, 1000),
    ECONOMY(450, 40, 1200),
    COMFORT(750, 60, 1500),
    PRIORITY(1050, 70, 2000);

    private final int maxSize;
    private final int maxWeight;
    private final int pricePerKm;

    TariffTypeRule(int maxSize, int maxWeight, int pricePerKm) {
        this.maxSize = maxSize;
        this.maxWeight = maxWeight;
        this.pricePerKm = pricePerKm;
    }
}
