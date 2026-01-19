package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.jdeliveryservice.component.NotificationAdapter;
import uzumtech.jdeliveryservice.constant.enums.ParcelStatus;
import uzumtech.jdeliveryservice.constant.enums.TariffType;
import uzumtech.jdeliveryservice.constant.enums.TariffTypeRule;
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
import uzumtech.jdeliveryservice.utils.MessageBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ParcelServiceImpl implements ParcelService {

    ConsumerRepository consumerRepository;
    ParcelRepository parcelRepository;
    ParcelMapper parcelMapper;
    AddressRepository addressRepository;
    MerchantRepository merchantRepository;
    NotificationAdapter notificationAdapter;

    @Override
    @Transactional
    public ParcelResponse createParcel(Long id,ParcelRequest parcelRequest) {
        var parcel = parcelMapper.toEntity(parcelRequest);

        var consumer = consumerRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("consumer not found"));

        var address = addressRepository
                .findByConsumer_Id(consumer.getId())
                .orElseThrow(() -> new DataNotFoundException("address not found"));

        var merchant = merchantRepository
                .findById(parcelRequest.merchantId())
                .orElseThrow(() -> new DataNotFoundException("Merchant not found"));

        parcel.setMerchant(merchant);
        parcel.setAddress(address);
        parcel.setConsumer(consumer);


        var save = parcelRepository.save(parcel);

        var message = MessageBuilder
                .ParcelRegistrationMessage(consumer.getFirstName(), parcel.getParcelStatus(), parcel.getId());

        log.info("Parcel saved with id:{}", parcel.getId());

        notificationAdapter.sendNotification(message,consumer.getEmail());

        log.info("Message sent to user:{}", consumer.getEmail());
        return parcelMapper.toResponse(save);
    }

    @Override
    @Transactional
    public void deleteParcelById(Long id) {
        ParcelEntity parcel = parcelRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Parcel not found"));
        parcel.setActive(false);

        log.info("Parcel deleted with id:{}", id);
    }

    @Override
    @Transactional
    public void updateParcelById(Long id, ParcelRequest parcelRequest) {
        var parcel = parcelRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Parcel not found"));

        parcelMapper.updateParcelFromDto(parcelRequest,parcel);

        log.info("Parcel updated with id:{}", id);
    }

    @Override
    public Page<ParcelResponse> getActiveParcelById(Long id, Pageable pageable) {
        Page<ParcelEntity> parcel = parcelRepository.findByActiveTrueAndConsumerId(id, pageable);
        return parcel.map(parcelMapper::toResponse);
    }

    @Override
    @Transactional
    public void updateStatusOfParcel(Long id, ParcelStatus parcelStatus) {
        ParcelEntity parcel = parcelRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Parcel not found"));
        parcel.setParcelStatus(parcelStatus);

        log.info("Parcel status updated with id:{}", id);

        String message = MessageBuilder.updateStatusMessage(id, parcelStatus);
        notificationAdapter.sendNotification(message,parcel.getConsumer().getEmail());

        log.info("Message sent to email with email:{}", parcel.getConsumer().getEmail());
    }
}
