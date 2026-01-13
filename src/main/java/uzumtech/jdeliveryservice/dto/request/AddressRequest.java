package uzumtech.jdeliveryservice.dto.request;

public record AddressRequest(
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
