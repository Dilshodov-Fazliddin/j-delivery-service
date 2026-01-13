package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uzumtech.jdeliveryservice.dto.request.ConsumerRequest;
import uzumtech.jdeliveryservice.dto.response.ConsumerResponse;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;

@Mapper(componentModel = "spring")
public interface ConsumerMapper {
    @Mapping(target = "id", ignore = true)
    ConsumerEntity toEntity(ConsumerRequest request);

    ConsumerResponse toResponse(ConsumerEntity entity);
}
