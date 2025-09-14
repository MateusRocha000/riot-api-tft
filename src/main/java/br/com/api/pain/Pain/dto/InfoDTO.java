package br.com.api.pain.Pain.dto;

import java.util.List;

public record InfoDTO(
        long gameId,
        int queueId,
        long gameDuration,
        List<TeamDTO> teams,
        List<ParticipantDTO> participants
) {
}
