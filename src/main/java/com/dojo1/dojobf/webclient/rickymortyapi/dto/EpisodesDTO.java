package com.dojo1.dojobf.webclient.rickymortyapi.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class EpisodesDTO {
    private InfoDTO info;
    private List<ResultsDTO> results;
}
