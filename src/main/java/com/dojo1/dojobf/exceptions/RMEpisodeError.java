package com.dojo1.dojobf.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RMEpisodeError {
    //FIXME this is an enum, not an exception. The exception should implement throwable or extend an Exception.

    EPISODE_NOT_FOUND_ERROR("Episode was not found");

    private final String message;
}