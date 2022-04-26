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
    private String name; //TODO Can name be empty?
    private String air_date; //TODO can air_date be 01.01.0001?
    private List<RMCharacterDTO> episode_characters;
}