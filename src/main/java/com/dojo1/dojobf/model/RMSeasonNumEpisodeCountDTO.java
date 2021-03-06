package com.dojo1.dojobf.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RMSeasonNumEpisodeCountDTO {
    private int seasonNumber;
    private int episodesCount;
}
