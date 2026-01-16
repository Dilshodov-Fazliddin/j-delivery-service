package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank(message = "Street is blank, please fill this field")
        String street,
        @NotBlank(message = "City is blank, please fill this field")
        String city,
        @NotBlank(message = "State is blank, please fill this field")
        String state,
        @NotBlank(message = "Postal code is blank, please fill this field")
        String postalCode,
        @NotBlank(message = "Country is blank, please fill this field")
        String country
) {
}
