package br.com.api.pain.Pain.exception;

import java.time.LocalDateTime;

/**
 * Standard error response object for API errors.
 * Contains information about the error that occurred.
 */
public class ErrorResponse {

    private final String title;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    /**
     * Creates a new ErrorResponse with the given details.
     *
     * @param title   A short title describing the error
     * @param message A more detailed message about the error
     * @param status  The HTTP status code associated with the error
     */
    public ErrorResponse(String title, String message, int status) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}