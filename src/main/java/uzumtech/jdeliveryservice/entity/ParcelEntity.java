package uzumtech.jdeliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;
import uzumtech.jdeliveryservice.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParcelEntity extends BaseEntity {

    @Column(nullable = false)
    Integer length;

    @Column(nullable = false)
    Integer width;

    @Column(nullable = false)
    Integer height;

    @Column(nullable = false)
    Double weight;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ParcelStatus parcelStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TariffType tariffType;

    Boolean active;

    @Column(nullable = false)
    Double price;

    @Column(nullable = false)
    Double distance;

    @Column(nullable = false)
    double latitudeFrom;

    @Column(nullable = false)
    double longitudeFrom;

    @Column(nullable = false)
    double latitudeTo;

    @Column(nullable = false)
    double longitudeTo;

    @ManyToOne
    @JoinColumn(name = "address_id")
    AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id")
    MerchantEntity merchant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumer_id")
    ConsumerEntity consumer;
}
