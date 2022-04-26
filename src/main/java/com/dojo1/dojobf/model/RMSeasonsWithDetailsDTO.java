package com.dojo1.dojobf.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RMSeasonsWithDetailsDTO {
    private String season; //TODO non descriptive var name. Can season be empty? Shouldn't this be a number?
    private List<RMEpisodeDTO> episodes; //TODO does this need a setter?
}
