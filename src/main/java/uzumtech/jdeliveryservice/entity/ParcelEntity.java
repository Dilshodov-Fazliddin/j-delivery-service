package uzumtech.jdeliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;

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
    ParcelStatus parcelStatus;

    @Column(nullable = false)
    TariffType tariffType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id")
    MerchantEntity merchant;
}
