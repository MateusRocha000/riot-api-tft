package br.com.api.tft.dto;

import java.util.List;

public record ParticipantDTO(
        String riotIdGameName,
        String riotIdTagline,
        String puuid,
        boolean win,
        int gold_left,
        int level,
        int placement,
        int players_eliminated,
        float time_eliminated,
        int total_damage_to_players,
        List<TraitDto> traits,
        List<UnitDto> units
) {
}
