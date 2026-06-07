package uzumtech.jdeliveryservice.dto.response;

public record ConsumerResponse(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
