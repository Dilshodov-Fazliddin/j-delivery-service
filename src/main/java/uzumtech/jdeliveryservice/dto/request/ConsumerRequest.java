package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConsumerRequest(
        @NotBlank(message = "first name is blank, please fill this field")
        String firstName,
        @NotBlank(message = "last name is blank, please fill this field")
        String lastName,
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",message = "Email must be valid")
        String email,
        @NotBlank(message = "phone number is blank, please fill this field")
        String phoneNumber,
        @NotBlank(message = "active is blank, please fill this field")
        Boolean active
) {
}
