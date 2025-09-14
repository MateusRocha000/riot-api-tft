package br.com.api.pain.Pain.controller;


import br.com.api.pain.Pain.dto.MatchDTO;
import br.com.api.pain.Pain.exception.ApiKeyException;
import br.com.api.pain.Pain.service.CSVExportService;
import br.com.api.pain.Pain.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for match-related endpoints.
 */
@RestController
@RequestMapping("/matches")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;

    @Autowired
    private CSVExportService csvExportService;

    /**
     * Gets match IDs for a player by PUUID.
     *
     * @param puuid The PUUID of the player
     * @return A list of match IDs
     */
    @GetMapping("/by-puuid/{puuid}")
    public ResponseEntity<List<String>> getMatchIds(@PathVariable String puuid) {
        logger.info("Received request to get match IDs for player with PUUID: {}", puuid);
        
        try {
            List<String> matchIds = matchService.getMatchIdsFromPuuid(puuid);
            logger.info("Successfully retrieved {} match IDs for player with PUUID: {}", matchIds.size(), puuid);
            return ResponseEntity.ok(matchIds);
        } catch (ApiKeyException e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("API key error while getting match IDs: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("Error while getting match IDs: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/by-filename/{filename}")
    public ResponseEntity<List<MatchDTO>> getMatchDataFromFile(@PathVariable String filename) {
        logger.info("Received request to get match data for file: {}", filename);

        try {
            Resource resource = new ClassPathResource(filename);
            File file = resource.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<MatchDTO> datas = new ArrayList<>();
            for (String id : bufferedReader.lines().collect(Collectors.toSet())) {
                logger.info("Getting data for match: {}", id);
                MatchDTO matchData = matchService.getMatchDataFromId(id);
                datas.add(matchData);
            }
            String[] sub = filename.split("\\d");
            String player = sub[0];
            csvExportService.exportPlayerDataToCSV(datas, player);
            return ResponseEntity.ok(datas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets detailed match data by match ID.
     *
     * @param matchId The match ID
     * @return The match data
     */
    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> getMatchData(@PathVariable String matchId) {
        logger.info("Received request to get match data for match ID: {}", matchId);
        
        try {
            MatchDTO matchData = matchService.getMatchDataFromId(matchId);
            logger.info("Successfully retrieved data for match ID: {}", matchId);
            return ResponseEntity.ok(matchData);
        } catch (ApiKeyException e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("API key error while getting match data: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Let the GlobalExceptionHandler handle this
            logger.error("Error while getting match data: {}", e.getMessage());
            throw e;
        }
    }
}
