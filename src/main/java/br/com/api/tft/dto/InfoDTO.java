package br.com.api.tft.dto;

import java.util.List;

public record InfoDTO(
        long gameId,
        int queue_id,
        long game_datetime,
        float game_length,
        String tft_game_type,
        String tft_set_core_name,
        int tft_set_number,
        int mapId,
        List<ParticipantDTO> participants
) {
}
