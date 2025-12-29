package br.com.api.tft.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * Handles specific exceptions and provides appropriate responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles ApiKeyException which is thrown when there are issues with the API key.
     * 
     * @param ex The ApiKeyException
     * @return A ResponseEntity with an error message and HTTP status code
     */
    @ExceptionHandler(ApiKeyException.class)
    public ResponseEntity<ErrorResponse> handleApiKeyException(ApiKeyException ex) {
        logger.error("API Key Exception: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                "API Key Error",
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
    
    /**
     * Handles FeignException.Forbidden (HTTP 403) which typically occurs when the Riot API key is invalid or expired.
     * 
     * @param ex The FeignException.Forbidden exception
     * @return A ResponseEntity with an error message and HTTP status code
     */
    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handleFeignForbiddenException(FeignException.Forbidden ex) {
        logger.error("Feign Forbidden Exception: {}", ex.getMessage());
        
        ErrorResponse errorResponse = new ErrorResponse(
                "API Key Error",
                "The Riot API key is invalid or has expired. Please generate a new API key from the Riot Developer Portal and update it in application.properties.",
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles general FeignException which can occur for various reasons when calling external APIs.
     * 
     * @param ex The FeignException
     * @return A ResponseEntity with an error message and HTTP status code
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.status());
        String message = "Error calling Riot API";
        
        // Provide more specific messages based on status codes
        if (ex.status() == 404) {
            message = "The requested resource was not found on the Riot API server.";
        } else if (ex.status() == 429) {
            message = "Rate limit exceeded. Too many requests to the Riot API.";
        } else if (ex.status() == 500) {
            message = "Internal server error from the Riot API.";
        } else if (ex.status() == 503) {
            message = "Riot API service is unavailable or under maintenance.";
        }
        
        ErrorResponse errorResponse = new ErrorResponse(
                "Riot API Error",
                message + " Status code: " + ex.status(),
                ex.status()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Handles all other exceptions not specifically handled by other exception handlers.
     * 
     * @param ex The exception
     * @return A ResponseEntity with an error message and HTTP status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Server Error",
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}