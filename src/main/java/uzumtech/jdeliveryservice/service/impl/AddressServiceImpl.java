package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.jdeliveryservice.dto.request.AddressRequest;
import uzumtech.jdeliveryservice.dto.response.AddressResponse;
import uzumtech.jdeliveryservice.entity.AddressEntity;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;
import uzumtech.jdeliveryservice.exception.DataNotFoundException;
import uzumtech.jdeliveryservice.mapper.AddressMapper;
import uzumtech.jdeliveryservice.repository.AddressRepository;
import uzumtech.jdeliveryservice.repository.ConsumerRepository;
import uzumtech.jdeliveryservice.service.AddressService;

@Service
@RequiredArgsConstructor
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
        return addressMapper.toResponse(save);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAddressById(Long id, AddressRequest addressRequest) {
        AddressEntity address = addressRepository
                .findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException("Address not found with id: " + id)
                );

        addressMapper.updateAddressFromDto(addressRequest,address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public AddressResponse getAddressByConsumerId(Long id) {
        var address = addressRepository
                .getAddressByConsumerId(id)
                .orElseThrow(() -> new DataNotFoundException("Address not found with id:" + id));

        return addressMapper.toResponse(address);
    }

}

