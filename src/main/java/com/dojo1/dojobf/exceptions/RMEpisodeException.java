package com.dojo1.dojobf.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RMEpisodeException extends RuntimeException{

    private final RMEpisodeError rmEpisodeError;
}