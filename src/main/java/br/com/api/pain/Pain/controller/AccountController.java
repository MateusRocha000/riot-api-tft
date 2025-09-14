package br.com.api.pain.Pain.controller;


import br.com.api.pain.Pain.dto.AccountResponseDTO;
import br.com.api.pain.Pain.exception.ApiKeyException;
import br.com.api.pain.Pain.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for account-related endpoints.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/{puuid}")
    public ResponseEntity<AccountResponseDTO> getAccountByPuuid(@PathVariable String puuid) {
        logger.info("Received request to get account by PUUID: {}", puuid);

        try {
            AccountResponseDTO account = accountService.getPlayerByPuuid(puuid);
            logger.info("Succesfully retrieved account for PUUID: {}", puuid);
            return ResponseEntity.ok(account);
        } catch (ApiKeyException e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("API key error while getting account: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("Error while getting account: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Gets account information by Riot ID (game name and tag line).
     *
     * @param gameName The game name part of the Riot ID
     * @param tagLine The tag line part of the Riot ID
     * @return The account information
     */
    @GetMapping("/{gameName}/{tagLine}")
    public ResponseEntity<AccountResponseDTO> getAccountByNickTag(@PathVariable String gameName, @PathVariable String tagLine) {
        logger.info("Received request to get account by Riot ID: {}/{}", gameName, tagLine);
        
        try {
            AccountResponseDTO account = accountService.getPlayerByNickTag(gameName, tagLine);
            logger.info("Successfully retrieved account for Riot ID: {}/{}", gameName, tagLine);
            return ResponseEntity.ok(account);
        } catch (ApiKeyException e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("API key error while getting account: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("Error while getting account: {}", e.getMessage());
            throw e;
        }
    }
}
