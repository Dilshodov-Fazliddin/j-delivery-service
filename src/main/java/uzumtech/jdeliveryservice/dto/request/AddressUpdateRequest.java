package uzumtech.jdeliveryservice.dto.request;

public record AddressUpdateRequest(
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
