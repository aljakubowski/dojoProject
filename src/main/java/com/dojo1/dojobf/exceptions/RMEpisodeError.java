package com.dojo1.dojobf.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RMEpisodeError {

    EPISODE_NOT_FOUND_ERROR("Episode was not found");

    private final String message;
}