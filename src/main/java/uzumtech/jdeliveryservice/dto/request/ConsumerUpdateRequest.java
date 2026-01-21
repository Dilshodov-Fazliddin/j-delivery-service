package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConsumerUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {}
