package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.*;
import uzumtech.jdeliveryservice.dto.request.ConsumerRequest;
import uzumtech.jdeliveryservice.dto.request.ConsumerUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.ConsumerResponse;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;

@Mapper(componentModel = "spring")
public interface ConsumerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", defaultValue = "true")
    ConsumerEntity toEntity(ConsumerRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateConsumerFromDto(ConsumerUpdateRequest dto, @MappingTarget ConsumerEntity entity);
   
    ConsumerResponse toResponse(ConsumerEntity entity);
}
