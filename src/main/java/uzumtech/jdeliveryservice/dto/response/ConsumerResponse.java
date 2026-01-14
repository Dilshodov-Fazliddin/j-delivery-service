package uzumtech.jdeliveryservice.dto.response;

import java.util.List;

public record ConsumerResponse(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
