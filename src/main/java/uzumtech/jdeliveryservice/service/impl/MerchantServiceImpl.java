package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uzumtech.jdeliveryservice.dto.request.MerchantRequest;
import uzumtech.jdeliveryservice.dto.response.MerchantResponse;
import uzumtech.jdeliveryservice.exception.DataNotFoundException;
import uzumtech.jdeliveryservice.mapper.MerchantMapper;
import uzumtech.jdeliveryservice.repository.MerchantRepository;
import uzumtech.jdeliveryservice.service.MerchantService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MerchantServiceImpl implements MerchantService {

    MerchantRepository merchantRepository;
    MerchantMapper merchantMapper;


    @Override
    public void saveMerchant(MerchantRequest merchantRequest) {
        var entity = merchantMapper.toEntity(merchantRequest);
        merchantRepository.save(entity);
    }

    @Override
    public MerchantResponse getMerchantInfoByName(String name) {
        var entity=merchantRepository
                .findByName(name)
                .orElseThrow(()->new DataNotFoundException("Your merchant not found name:" + name));
        return merchantMapper.toResponse(entity);
    }
}
