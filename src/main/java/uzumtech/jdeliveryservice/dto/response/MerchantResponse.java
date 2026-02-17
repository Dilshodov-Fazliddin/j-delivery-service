package uzumtech.jdeliveryservice.dto.response;

public record MerchantResponse(
        String name,
        String login,
        String password,
        Long id
) {
}
