package br.com.api.tft.service;

import br.com.api.tft.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CSVExportService {

    private static final Logger logger = LoggerFactory.getLogger(CSVExportService.class);

    public String exportTraitDataToCSV(List<MatchDTO> data, String playerName) throws IOException {
        String filename = playerName + "_TFT_Ranked_Traits_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("match_id, date_time, game_duration, game_type, set_name, set_number, map_id, player_nick, win, level, " +
                    "gold_left, placement, players_eliminated, time_eliminated, damage_to_players, trait_name, trait_num_units, trait_style, trait_current_tier, trait_total_tier\n"
            );

            for (MatchDTO matchDTO : data) {
                for (ParticipantDTO p : matchDTO.info().participants()) {
                    for (TraitDto t : p.traits()) {
                        writer.write(String.format(
                            "%s,%d,%f,%s,%s,%d,%d,%s,%s,%d," +
                            "%d,%d,%d,%f,%d,%s,%d,%d,%d,%d\n",
                            matchDTO.metadata().match_id(), matchDTO.info().game_datetime(), matchDTO.info().game_length(), matchDTO.info().tft_game_type(), matchDTO.info().tft_set_core_name(), matchDTO.info().tft_set_number(), matchDTO.info().mapId(), p.riotIdGameName() + "#" + p.riotIdTagline(), p.win(), p.level(),
                            p.gold_left(), p.placement(), p.players_eliminated(), p.time_eliminated(), p.total_damage_to_players(), t.name(), t.num_units(), t.style(), t.tier_current(), t.tier_total()
                        ));
                    }
                }
            }
        }
        logger.info("CSV Exported: {}", filename);
        return filename;
    }

    public String exportUnitDataToCSV(List<MatchDTO> data, String playerName) throws IOException {
        String filename = playerName + "_TFT_Ranked_Units_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("match_id, date_time, game_duration, game_type, set_name, set_number, map_id, player_nick, win, level, " +
                    "gold_left, placement, players_eliminated, time_eliminated, damage_to_players, trait_name, trait_num_units, trait_style, trait_current_tier, trait_total_tier\n"
            );

            for (MatchDTO matchDTO : data) {
                for (ParticipantDTO p : matchDTO.info().participants()) {
                    for (UnitDto u : p.units()) {
                        for (String item : u.itemNames()) {
                            writer.write(String.format(
                                    "%s,%d,%f,%s,%s,%d,%d,%s,%s,%d," +
                                    "%d,%d,%d,%f,%d,%s,%s,%s,%d,%d\n",
                                    matchDTO.metadata().match_id(), matchDTO.info().game_datetime(), matchDTO.info().game_length(), matchDTO.info().tft_game_type(), matchDTO.info().tft_set_core_name(), matchDTO.info().tft_set_number(), matchDTO.info().mapId(), p.riotIdGameName() + "#" + p.riotIdTagline(), p.win(), p.level(),
                                    p.gold_left(), p.placement(), p.players_eliminated(), p.time_eliminated(), p.total_damage_to_players(), u.character_id(), item, u.name(), u.rarity(), u.tier()
                            ));
                        }
                    }
                }
            }
        }
        logger.info("CSV Exported: {}", filename);
        return filename;
    }
}
