package br.com.api.pain.Pain.dto;

public record TeamDTO(
        int teamId,
        boolean win,
        ObjectivesDTO objectivesDTO
) {
}
