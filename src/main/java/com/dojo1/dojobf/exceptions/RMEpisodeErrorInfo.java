package com.dojo1.dojobf.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RMEpisodeErrorInfo {
    // FIXME an Exception should extend an Exception class or implement throwable.
    private final String message;
}