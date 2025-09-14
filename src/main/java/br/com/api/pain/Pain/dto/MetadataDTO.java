package br.com.api.pain.Pain.dto;

import java.util.List;

public record MetadataDTO(
        String matchId,
        List<String> participants
) {
}
