package uzumtech.jdeliveryservice.dto.request;

import lombok.Builder;

@Builder
public record NotificationSmsRequest(
        String content,
        String email
) {
}
