package uzumtech.jdeliveryservice.service;

import uzumtech.jdeliveryservice.dto.request.MerchantRequest;
import uzumtech.jdeliveryservice.dto.response.MerchantResponse;

public interface MerchantService {
    void saveMerchant(MerchantRequest merchantRequest);
    MerchantResponse getMerchantInfoByName(String name);
}
