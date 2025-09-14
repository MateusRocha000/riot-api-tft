package br.com.api.pain.Pain.dto;

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