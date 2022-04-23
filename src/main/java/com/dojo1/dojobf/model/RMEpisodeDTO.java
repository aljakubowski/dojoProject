package com.dojo1.dojobf.model;

import com.dojo1.dojobf.webclient.rickymortyapi.dto.RMCharacterDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RMEpisodeDTO {
    private String season;
    private String name;
    private String air_date;
    private List<RMCharacterDTO> episode_characters;
}