package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.*;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.response.ParcelResponse;
import uzumtech.jdeliveryservice.entity.ParcelEntity;

@Mapper(componentModel = "spring")
public interface ParcelMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", defaultValue = "true")
    ParcelEntity toEntity(ParcelRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateParcelFromDto(ParcelRequest dto, @MappingTarget ParcelEntity entity);

    ParcelResponse toResponse(ParcelEntity parcel);
}
