package br.com.api.pain.Pain.dto;

import java.util.List;

public record PerkStyleDTO (
        String description,
        int style,
        List<PerkStyleSelectionDTO> selections
) {
}
