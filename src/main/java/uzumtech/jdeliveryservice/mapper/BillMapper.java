package uzumtech.jdeliveryservice.mapper;

import org.mapstruct.Mapper;
import uzumtech.jdeliveryservice.dto.request.BillRequest;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.entity.ParcelEntity;

@Mapper(componentModel = "spring")
public interface BillMapper {

    BillRequest toBillRequest(ParcelRequest parcelRequest);
}
