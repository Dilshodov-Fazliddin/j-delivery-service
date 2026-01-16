package uzumtech.jdeliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
