package uzumtech.jdeliveryservice.dto.request;

public record ConsumerRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
