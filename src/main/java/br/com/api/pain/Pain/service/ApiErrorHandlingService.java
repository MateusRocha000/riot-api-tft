package br.com.api.pain.Pain.service;

import br.com.api.pain.Pain.exception.ApiKeyException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * Service that provides error handling for API calls.
 * Wraps Feign client calls with appropriate exception handling.
 */
@Service
public class ApiErrorHandlingService {

    private static final Logger logger = LoggerFactory.getLogger(ApiErrorHandlingService.class);

    /**
     * Executes an API call with proper error handling.
     * Catches FeignExceptions and translates them into more user-friendly exceptions.
     *
     * @param apiCall The API call to execute, wrapped in a Supplier
     * @param <T>     The return type of the API call
     * @return The result of the API call
     * @throws ApiKeyException if there's an issue with the API key
     * @throws RuntimeException for other API errors
     */
    public <T> T executeWithErrorHandling(Supplier<T> apiCall) {
        try {
            return apiCall.get();
        } catch (FeignException.Forbidden ex) {
            logger.error("API key error: {}", ex.getMessage());
            throw new ApiKeyException("The Riot API key is invalid or has expired. Please generate a new API key.", ex);
        } catch (FeignException.NotFound ex) {
            logger.error("Resource not found: {}", ex.getMessage());
            throw new RuntimeException("The requested resource was not found on the Riot API server.", ex);
        } catch (FeignException.TooManyRequests ex) {
            logger.error("Rate limit exceeded: {}", ex.getMessage());
            throw new RuntimeException("Rate limit exceeded. Too many requests to the Riot API.", ex);
        } catch (FeignException ex) {
            logger.error("API error: {} - {}", ex.status(), ex.getMessage());
            throw new RuntimeException("Error calling Riot API: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + ex.getMessage(), ex);
        }
    }
}