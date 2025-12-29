package br.com.api.tft.service;


import br.com.api.tft.client.MatchClient;
import br.com.api.tft.dto.AccountResponseDTO;
import br.com.api.tft.dto.MatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchClient matchClient;

    @Autowired
    private ApiErrorHandlingService errorHandlingService;

    private AccountService accountService;

    /**
     * Gets match IDs for a player by PUUID with error handling.
     *
     * @param puuid The PUUID of the player
     * @return A list of match IDs
     */
    public List<String> getMatchIdsFromPuuid(String puuid) {
        AccountResponseDTO dto = accountService.getPlayerByPuuid(puuid);
        logger.info("Getting match IDs for player: {}", dto.gameName() + "#" + dto.tagLine());
        String filename = dto.gameName() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
        List<String> matchIds = matchClient.getMatchIDsFromPuuid(puuid);
        try (FileWriter writer = new FileWriter(filename)) {
            for (String id : matchIds) {
                writer.write(String.format("%s\n", id));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return errorHandlingService.executeWithErrorHandling(() -> matchClient.getMatchIDsFromPuuid(puuid));
    }

    /**
     * Gets match IDs for a player by PUUID with pagination support and error handling.
     *
     * @param puuid The PUUID of the player
     * @param count The number of match IDs to retrieve
     * @return A list of match IDs
     */
    public List<String> getMatchIdsFromPuuid(String puuid, int count) {
        logger.info("Getting {} match IDs for player with PUUID: {}", count, puuid);
        return errorHandlingService.executeWithErrorHandling(() -> matchClient.getMatchIDsFromPuuid(count));
    }

    /**
     * Gets detailed match data by match ID with error handling.
     *
     * @param matchId The match ID
     * @return The match data
     */
    public MatchDTO getMatchDataFromId(String matchId) {
        logger.info("Getting match data for match ID: {}", matchId);
        return errorHandlingService.executeWithErrorHandling(() -> matchClient.getMatchDataById(matchId));
    }
}
