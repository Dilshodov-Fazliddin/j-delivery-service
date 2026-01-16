package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;
import uzumtech.jdeliveryservice.dto.response.ParcelResponse;
import uzumtech.jdeliveryservice.entity.ParcelEntity;
import uzumtech.jdeliveryservice.exception.DataNotFoundException;
import uzumtech.jdeliveryservice.mapper.ParcelMapper;
import uzumtech.jdeliveryservice.repository.AddressRepository;
import uzumtech.jdeliveryservice.repository.ConsumerRepository;
import uzumtech.jdeliveryservice.repository.MerchantRepository;
import uzumtech.jdeliveryservice.repository.ParcelRepository;
import uzumtech.jdeliveryservice.service.ParcelService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ParcelServiceImpl implements ParcelService {

    ConsumerRepository consumerRepository;
    ParcelRepository parcelRepository;
    ParcelMapper parcelMapper;
    AddressRepository addressRepository;
    MerchantRepository merchantRepository;

    @Override
    @Transactional
    public ParcelResponse createParcel(Long id,ParcelRequest parcelRequest) {
        var parcel = parcelMapper.toEntity(parcelRequest);

        var consumer = consumerRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Parcel not found"));

        var address = addressRepository
                .findByConsumer_Id(consumer.getId())
                .orElseThrow(() -> new DataNotFoundException("Consumer not found"));

        var merchant = merchantRepository
                .findById(parcelRequest.merchantId())
                .orElseThrow(() -> new DataNotFoundException("Merchant not found"));

        parcel.setMerchant(merchant);
        parcel.setAddress(address);
        parcel.setConsumer(consumer);
        parcel.setParcelStatus(ParcelStatus.CREATED);

        var save = parcelRepository.save(parcel);
        return parcelMapper.toResponse(save);
    }

    @Override
    @Transactional
    public void deleteParcelById(Long id) {
        ParcelEntity parcel = parcelRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Parcel not found"));
        parcel.setActive(false);
    }

    @Override
    @Transactional
    public void updateParcelById(Long id, ParcelRequest parcelRequest) {
        var parcel = parcelRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Parcel not found"));

        parcelMapper.updateParcelFromDto(parcelRequest,parcel);
    }

    @Override
    public Page<ParcelResponse> getActiveParcelById(Long id, Pageable pageable) {
        Page<ParcelEntity> parcel = parcelRepository.findByActiveTrueAndConsumerId(id, pageable);
        return parcel.map(parcelMapper::toResponse);
    }
}
