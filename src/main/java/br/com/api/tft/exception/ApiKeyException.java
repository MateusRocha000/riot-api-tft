package br.com.api.tft.exception;

/**
 * Exception thrown when there are issues with the API key.
 * This can include invalid, expired, or missing API keys.
 */
public class ApiKeyException extends RuntimeException {

    /**
     * Creates a new ApiKeyException with a default message.
     */
    public ApiKeyException() {
        super("API key is invalid or expired. Please generate a new key from the Riot Developer Portal.");
    }

    /**
     * Creates a new ApiKeyException with a custom message.
     *
     * @param message The custom error message
     */
    public ApiKeyException(String message) {
        super(message);
    }

    /**
     * Creates a new ApiKeyException with a custom message and cause.
     *
     * @param message The custom error message
     * @param cause   The cause of the exception
     */
    public ApiKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}