package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.request.AddressUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;
import uzumtech.jdeliveryservice.entity.AddressEntity;
import uzumtech.jdeliveryservice.exception.DataNotFoundException;
import uzumtech.jdeliveryservice.mapper.AddressMapper;
import uzumtech.jdeliveryservice.repository.AddressRepository;
import uzumtech.jdeliveryservice.repository.ConsumerRepository;
import uzumtech.jdeliveryservice.service.AddressService;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;
    AddressMapper addressMapper;
    ConsumerRepository consumerRepository;
    @Override
    public AddressResponse createAddress(AddressRequest addressRequest,Long consumerId) {
        var consumer = consumerRepository
                .findById(consumerId)
                .orElseThrow(() -> new DataNotFoundException("consumer not found"));

        var entity = addressMapper.toEntity(addressRequest);
        entity.setConsumer(consumer);

        var save = addressRepository.save(entity);
        log.info("save address {}", save.getId());
        return addressMapper.toResponse(save);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAddressById(Long id, AddressUpdateRequest addressRequest) {
        AddressEntity address = addressRepository
                .findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException("Address not found with id: " + id)
                );

        log.info("updated address {}", address.getId());
        addressMapper.updateAddressFromDto(addressRequest,address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
        log.info("address deleted with id + {}", id);
    }

    @Override
    public AddressResponse getAddressByConsumerId(Long id) {
        var address = addressRepository
                .getAddressByConsumerId(id)
                .orElseThrow(() -> new DataNotFoundException("Address not found with id:" + id));

        return addressMapper.toResponse(address);
    }

}

