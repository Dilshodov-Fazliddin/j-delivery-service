package uzumtech.jdeliveryservice.service;

import uzumtech.jdeliveryservice.dto.request.MerchantRequest;

public interface MerchantService {
    void saveMerchant(MerchantRequest merchantRequest);
}
