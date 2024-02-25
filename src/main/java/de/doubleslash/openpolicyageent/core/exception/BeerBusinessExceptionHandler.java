package de.doubleslash.openpolicyageent.core.exception;

import java.util.Date;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@Order(400)
@ControllerAdvice
public class BeerBusinessExceptionHandler {

    /**
     * Logs and maps the exception to a 422 client response.
     *
     * @param e The exception to be mapped
     * @return A response object to be returned to the client
     */
    @ExceptionHandler(BeerBusinessException.class)
    public ResponseEntity<ErrorResponse> toResponse(final HttpServletRequest request,
            final BeerBusinessException e) {

        // get request url and remote address
        String url = null;
        String remoteAddress = "unknown";
        if (request != null) {
            url = request.getRequestURL() != null ? request.getRequestURL().toString() : null;
            remoteAddress = request.getRemoteAddr();
        }

        // Bean validation errors are thrown as BusinessException
        // These will be mapped to ClientErrors(HTTP 422)
        final HttpStatusCode status = e.getHttpStatus() == null ? HttpStatus.UNPROCESSABLE_ENTITY : e.getHttpStatus();

        final ErrorResponse apiBusinessErrorResponse =
                ErrorResponse.builder()
                        .requestUrl(url)
                        .errorCode(e.getErrorCode())
                        .message(e.getMessage())
                        .httpStatusCode(e.getHttpStatus().value())
                        .requestTimestamp(new Date())
                        .build();
        return ResponseEntity.status(status).body(apiBusinessErrorResponse);
    }

}
