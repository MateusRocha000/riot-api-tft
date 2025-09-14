package br.com.api.pain.Pain.dto;

import java.util.List;

public record PerksDTO(
        PerkStatsDTO statPerks,
        List<PerkStyleDTO> styles
) {
}
