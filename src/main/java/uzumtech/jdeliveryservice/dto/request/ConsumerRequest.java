package uzumtech.jdeliveryservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConsumerRequest(
        @NotBlank(message = "First name is blank, please fill this field")
        String firstName,
        @NotBlank(message = "Last name is blank, please fill this field")
        String lastName,
        @NotBlank(message = "Email is blank, please fill this field")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must be valid")
        String email,
        @NotBlank(message = "Phone number is blank, please fill this field")
        String phoneNumber,
        Boolean active
) {
}
