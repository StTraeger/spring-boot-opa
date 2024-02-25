package de.doubleslash.openpolicyageent.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public class BeerBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final transient Object result;
    private final String errorCode;
    private final HttpStatusCode httpStatus;

    /**
     * Default constructor using ErrorId
     *
     * @param errorId    the {@link ErrorId} which wraps the error id and message
     * @param httpStatus the {@link HttpStatus}
     */
    public BeerBusinessException(final ErrorId errorId, final HttpStatusCode httpStatus, final Object... params) {
        this(errorId.getCode(), errorId.getDescription(params), httpStatus);
    }

    /**
     * Default constructor
     *
     * @param message    the message
     * @param errorCode  the Log error code
     * @param httpStatus the httpStatus
     */
    public BeerBusinessException(final String errorCode, final String message, final HttpStatusCode httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.result = null;
    }

}
