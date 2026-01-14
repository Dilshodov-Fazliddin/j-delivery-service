package uzumtech.jdeliveryservice.dto.response;

public record AddressResponse(
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
