package br.com.api.tft.dto;

import java.util.List;

public record UnitDto(
        List<Integer> items,
        String character_id,
        List<String> itemNames,
        String name,
        int rarity,
        int tier
) {
}
