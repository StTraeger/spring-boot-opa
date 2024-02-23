package de.doubleslash.openpolicyageent.business.exception;

import java.util.Date;

import lombok.Builder;

@Builder
public record ErrorResponse(

        String requestUrl,
        String errorCode,
        String message,
        int httpStatusCode,
        Date requestTimestamp
) {

}
