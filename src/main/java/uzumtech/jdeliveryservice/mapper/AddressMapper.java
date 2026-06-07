package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.*;
import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.request.AddressUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;
import uzumtech.jdeliveryservice.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    AddressEntity toEntity(AddressRequest address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDto(AddressUpdateRequest dto, @MappingTarget AddressEntity entity);

    AddressResponse toResponse(AddressEntity address);
}
