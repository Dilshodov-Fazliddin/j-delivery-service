package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.*;
import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.request.ConsumerUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;
import uzumtech.jdeliveryservice.entity.AddressEntity;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    AddressEntity toEntity(AddressRequest address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDto(AddressRequest dto, @MappingTarget AddressEntity entity);

    AddressResponse toResponse(AddressEntity address);
}
