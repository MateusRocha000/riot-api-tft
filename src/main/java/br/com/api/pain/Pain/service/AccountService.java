package br.com.api.pain.Pain.service;


import br.com.api.pain.Pain.client.AccountClient;
import br.com.api.pain.Pain.dto.AccountResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private ApiErrorHandlingService errorHandlingService;

    public AccountResponseDTO getPlayerByPuuid(String puuid) {
        logger.info("Getting account by PUUID: {}", puuid);
        return errorHandlingService.executeWithErrorHandling(() -> accountClient.getAccountByPuuid(puuid));
    }

    /**
     * Gets account information by Riot ID (game name and tag line) with error handling.
     *
     * @param gameName The game name part of the Riot ID
     * @param tagLine The tag line part of the Riot ID
     * @return The account information
     */
    public AccountResponseDTO getPlayerByNickTag(String gameName, String tagLine) {
        logger.info("Getting account by Riot ID: {}/{}", gameName, tagLine);
        return errorHandlingService.executeWithErrorHandling(() -> accountClient.getAccountByNickTag(gameName, tagLine));
    }
}
