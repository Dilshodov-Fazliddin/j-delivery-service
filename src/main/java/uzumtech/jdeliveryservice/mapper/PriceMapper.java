package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.*;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.request.PriceRequest;
import uzumtech.jdeliveryservice.dto.response.PriceResponse;
import uzumtech.jdeliveryservice.entity.ParcelEntity;
import uzumtech.jdeliveryservice.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", defaultValue = "true")
    PriceEntity toEntity(PriceRequest request);
    PriceResponse toResponse(PriceEntity price);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePriceFromDto(PriceRequest dto, @MappingTarget PriceEntity entity);
}
