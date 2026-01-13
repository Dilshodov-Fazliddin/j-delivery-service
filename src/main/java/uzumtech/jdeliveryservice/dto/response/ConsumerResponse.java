package uzumtech.jdeliveryservice.dto.response;

import java.util.List;

public record ConsumerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
