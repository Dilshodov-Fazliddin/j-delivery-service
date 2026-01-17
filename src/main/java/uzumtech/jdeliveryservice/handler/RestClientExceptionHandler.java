package uzumtech.jdeliveryservice.handler;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import uzumtech.jdeliveryservice.exception.HttpClientException;
import uzumtech.jdeliveryservice.exception.HttpServerException;

import java.io.IOException;
import java.net.URI;

@Component
public class RestClientExceptionHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        var errorMessage = String.join(" ","Http error on " + method + " " + url + ": " + response.getStatusCode());
        if (response.getStatusCode().is4xxClientError()) {
            throw new HttpClientException(errorMessage, response.getStatusCode());
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new HttpServerException(errorMessage, response.getStatusCode());
        }
    }
}
