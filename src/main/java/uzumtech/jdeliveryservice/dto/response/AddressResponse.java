package uzumtech.jdeliveryservice.dto.response;

public record AddressResponse(
        Long id,
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
