package uzumtech.jdeliveryservice.component;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import uzumtech.jdeliveryservice.dto.request.NotificationSmsRequest;
import uzumtech.jdeliveryservice.dto.request.ParcelRequest;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class NotificationAdapter {
    final RestClient restClient;

    @Value("${notification.service.merchantLogin}")
    String merchantLogin;
    @Value("${notification.service.merchantPassword}")
    String merchantPassword;
    @Value("${notification.service.id}")
    String id;
    @Value("${notification.service.url.mail}")
    String url;


    public void sendNotification(String message,String email) {
        var result = restClient.post()
                .uri(url + id )
                .headers(headers->{
                    headers.setBasicAuth(merchantLogin,merchantPassword);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(new NotificationSmsRequest(message,email))
                .retrieve()
                .toBodilessEntity();

        log.info("{}", result);
    }
}
