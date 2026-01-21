package uzumtech.jdeliveryservice.service;

import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.request.AddressUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest,Long consumerId);
    void updateAddressById(Long id, AddressUpdateRequest addressRequest);
    void deleteAddressById(Long id);
    AddressResponse getAddressByConsumerId(Long id);
}
