package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.response.ParcelResponse;
import uzumtech.jdeliveryservice.entity.ParcelEntity;

@Mapper(componentModel = "spring")
public interface ParcelMapper {
    @Mapping(target = "id", ignore = true)
    ParcelEntity toEntity(ParcelRequest request);
    ParcelResponse toResponse(ParcelEntity parcel);
}
