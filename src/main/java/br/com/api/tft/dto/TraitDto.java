package br.com.api.tft.dto;

public record TraitDto(
        String name,
        int num_units,
        int style,
        int tier_current,
        int tier_total
) {
}
