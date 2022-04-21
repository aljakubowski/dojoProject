package com.dojo1.dojobf.webclient.chuckapi.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class JokeWithTimeDto {
    private String updated_at;
    private String value;
    private List<String> categories;
}