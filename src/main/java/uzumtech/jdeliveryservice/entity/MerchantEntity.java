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
public class MerchantEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    String name;
    @Column(unique = true, nullable = false)
    String login;
    String password;
}
