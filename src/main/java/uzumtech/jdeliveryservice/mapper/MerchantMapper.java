package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uzumtech.jdeliveryservice.dto.request.MerchantRequest;
import uzumtech.jdeliveryservice.dto.response.MerchantResponse;
import uzumtech.jdeliveryservice.entity.MerchantEntity;

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    @Mapping(target = "id", ignore = true)
    MerchantEntity toEntity(MerchantRequest merchantRequest);

    MerchantResponse toResponse(MerchantEntity merchantEntity);
}
