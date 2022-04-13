package com.dojo1.dojobf.service;

import com.dojo1.dojobf.model.ChuckJokeDto;
import com.dojo1.dojobf.webclient.ChuckApiWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChuckApiService {

    private final ChuckApiWebClient chuckApiWebClient;

    public ChuckJokeDto getRandomJoke() {
        return getJokeDtoWithDateFormatted(chuckApiWebClient.getRandomChuckJoke());
    }

    public ChuckJokeDto getRandomJoke(String category) {
        return getJokeDtoWithDateFormatted(chuckApiWebClient.getRandomChuckJokeByCategory(category));
    }

    private ChuckJokeDto getJokeDtoWithDateFormatted(ChuckJokeDto chuckJokeDto) {
        String time = chuckJokeDto.getTime();
        chuckJokeDto.setTime(time.substring(0, time.indexOf(' ')));
        return chuckJokeDto;
    }

}