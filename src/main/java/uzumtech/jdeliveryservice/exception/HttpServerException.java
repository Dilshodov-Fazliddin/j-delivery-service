package uzumtech.jdeliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import uzumtech.jdeliveryservice.constant.enums.ErrorType;

import static uzumtech.jdeliveryservice.constant.enums.Error.HTTP_SERVICE_ERROR_CODE;


public class HttpServerException extends ApplicationException {

    public HttpServerException(String message, HttpStatusCode status) {
        super(HTTP_SERVICE_ERROR_CODE.getCode(), message, ErrorType.EXTERNAL, HttpStatus.valueOf(status.value()));
    }
}
