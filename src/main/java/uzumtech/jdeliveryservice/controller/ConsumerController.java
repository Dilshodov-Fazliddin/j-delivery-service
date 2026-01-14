package uzumtech.jdeliveryservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uzumtech.jdeliveryservice.dto.request.ConsumerRequest;
import uzumtech.jdeliveryservice.dto.request.ConsumerUpdateRequest;
import uzumtech.jdeliveryservice.dto.response.ConsumerResponse;
import uzumtech.jdeliveryservice.service.ConsumerService;

@RestController
@RequestMapping("/api/delivery/consumers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ConsumerController {

    ConsumerService consumerService;

    @PostMapping
    public ConsumerResponse createConsumer(
            @RequestBody ConsumerRequest consumerRequest
    ) {
        return consumerService.createConsumer(consumerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteConsumer(@PathVariable Long id) {
        consumerService.deleteConsumer(id);
    }

    @PatchMapping("/{id}")
    public void updateConsumer(
            @PathVariable Long id,
            @RequestBody ConsumerUpdateRequest updateRequest
    ) {
        consumerService.updateConsumer(id, updateRequest);
    }
}
