package uzumtech.jdeliveryservice.dto.request;

import lombok.Builder;

@Builder
public record NotificationSmsRequest(
        Receiver receiver,
        String type,
        String text
) {
}
