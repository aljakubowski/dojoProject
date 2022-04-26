package com.dojo1.dojobf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RMSeasonsListDTO {
    private List<RMSeasonNumEpisodeCountDTO> listOfEpisodesInSeason; //TODO does this require a setter?
}
