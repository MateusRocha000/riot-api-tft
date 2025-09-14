package br.com.api.pain.Pain.dto;

public record ObjectivesDTO(
        ObjectiveDTO baron,
        ObjectiveDTO champion,
        ObjectiveDTO dragon,
        ObjectiveDTO horde,
        ObjectiveDTO inhibitor,
        ObjectiveDTO riftHerald,
        ObjectiveDTO tower
) {
}
