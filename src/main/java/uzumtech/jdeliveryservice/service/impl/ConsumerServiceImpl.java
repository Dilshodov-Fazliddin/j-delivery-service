package uzumtech.jdeliveryservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uzumtech.jdeliveryservice.dto.request.ConsumerRequest;
import uzumtech.jdeliveryservice.dto.request.ConsumerUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.ConsumerResponse;
import uzumtech.jdeliveryservice.entity.ConsumerEntity;
import uzumtech.jdeliveryservice.exception.DataNotFoundException;
import uzumtech.jdeliveryservice.mapper.ConsumerMapper;
import uzumtech.jdeliveryservice.repository.ConsumerRepository;
import uzumtech.jdeliveryservice.service.ConsumerService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
public class ConsumerServiceImpl implements ConsumerService {
    ConsumerRepository consumerRepository;
    ConsumerMapper consumerMapper;

    @Override
    public ConsumerResponse createConsumer(ConsumerRequest consumerRequest) {
        var entity = consumerMapper.toEntity(consumerRequest);
        var save = consumerRepository.save(entity);
        return consumerMapper.toResponse(save);
    }

    @Override
    public void deleteConsumer(Long id) {
        var entity = consumerRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Consumer not found"));
        entity.setActive(false);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateConsumer(Long id, ConsumerUpdateRequest updateRequest) {

        var entity = consumerRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Consumer not found"));

        consumerMapper.updateConsumerFromDto(updateRequest, entity);
    }
}
