package uzumtech.jdeliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import uzumtech.jdeliveryservice.entity.base.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressEntity extends BaseEntity {
    @Column(nullable = false)
    String country;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    String street;
    @Column(nullable = false)
    String postalCode;
    @Column(nullable = false)
    String state;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumer_id")
    ConsumerEntity consumer;
}
