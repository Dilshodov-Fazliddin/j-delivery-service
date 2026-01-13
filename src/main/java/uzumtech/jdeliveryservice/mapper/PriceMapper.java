package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uzumtech.jdeliveryservice.dto.request.PriceRequest;
import uzumtech.jdeliveryservice.dto.response.PriceResponse;
import uzumtech.jdeliveryservice.entity.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    @Mapping(target = "id", ignore = true)
    PriceEntity toEntity(PriceRequest request);
    PriceResponse toResponse(PriceEntity price);
}
