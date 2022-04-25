package com.dojo1.dojobf.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RMSeasonsWithDetailsDTO {
    private String season;
    private List<RMEpisodeDTO> episodes;
}
