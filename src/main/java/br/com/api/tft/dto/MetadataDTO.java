package br.com.api.tft.dto;

import java.util.List;

public record MetadataDTO(
        String match_id,
        List<String> participants
) {
}
