package uzumtech.jdeliveryservice.service;

import uzumtech.jdeliveryservice.dto.request.ConsumerRequest;
import uzumtech.jdeliveryservice.dto.request.ConsumerUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.ConsumerResponse;

public interface ConsumerService {
    ConsumerResponse createConsumer(ConsumerRequest consumerRequest);
    void deleteConsumer(Long id);
    void updateConsumer(Long id, ConsumerUpdateRequest updateRequest);

}
