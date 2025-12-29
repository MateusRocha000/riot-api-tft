package br.com.api.tft.dto;

public record SummonerDTO(
        String id,
        String accountId,
        String puuid,
        String name,
        int profileIconId,
        long revisionDate,
        int summonerLevel
) {
}