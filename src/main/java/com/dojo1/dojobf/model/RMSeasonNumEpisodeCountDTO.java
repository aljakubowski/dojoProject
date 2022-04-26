package com.dojo1.dojobf.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RMSeasonNumEpisodeCountDTO {
    private int seasonNumber; //TODO can episode number be <0?
    private int episodesCount; //TODO can episode count be <0?
}
