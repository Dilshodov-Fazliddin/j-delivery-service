package uzumtech.jdeliveryservice.dto.request;

public record ConsumerUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber

) {}
