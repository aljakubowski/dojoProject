package com.dojo1.dojobf.webclient.rickymortyapi.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResultsDTO {
    private String name;
    private String air_date;
    private String episode;
    private String url;
    private List<String> characters;
}
