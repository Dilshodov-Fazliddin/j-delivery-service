package uzumtech.jdeliveryservice.service;

import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest,Long consumerId);
    void updateAddressById(Long id,AddressRequest addressRequest);
    void deleteAddressById(Long id);
    AddressResponse getAddressByConsumerId(Long id);
}
