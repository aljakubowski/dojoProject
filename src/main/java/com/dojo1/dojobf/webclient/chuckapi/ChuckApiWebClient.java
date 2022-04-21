package com.dojo1.dojobf.webclient.chuckapi;

import com.dojo1.dojobf.exceptions.JokeNotFoundException;
import com.dojo1.dojobf.model.ChuckJokeDto;
import com.dojo1.dojobf.webclient.chuckapi.dto.JokeWithTimeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Spring web client
 * converts json to java objects
 */

/*
        https://api.chucknorris.io/jokes/random
        https://api.chucknorris.io/jokes/random?category={category}

        https://api.chucknorris.io/jokes/categories
        https://api.chucknorris.io/jokes/search?query={query}
 */
@Component
@Slf4j
public class ChuckApiWebClient {

    public static final String CHUCK_URL = "https://api.chucknorris.io/jokes/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ChuckJokeDto getRandomChuckJoke() {

        JokeWithTimeDto jokeWithTimeDto;
        try {
            jokeWithTimeDto = createRequest("random", JokeWithTimeDto.class);
        } catch (Exception e) {
            throw new JokeNotFoundException("Joke was not found ;,(");
        }

        return ChuckJokeDto.builder()
                .time(jokeWithTimeDto.getUpdated_at())
                .joke(jokeWithTimeDto.getValue())
                .build();
    }


    public ChuckJokeDto getRandomChuckJokeByCategory(String category) {
        JokeWithTimeDto jokeWithTimeDto;
        try {
            jokeWithTimeDto = createRequest("random?category=" + category, JokeWithTimeDto.class);
        } catch (Exception e) {
            throw new JokeNotFoundException("Joke category was not found. Try another.");
        }

        return ChuckJokeDto.builder()
                .time(jokeWithTimeDto.getUpdated_at())
                .joke(jokeWithTimeDto.getValue())
                .category(jokeWithTimeDto.getCategories().get(0))
                .build();
    }


    private <T> T createRequest(String url, Class<T> responseType) {
        return restTemplate.getForObject(CHUCK_URL + url, responseType);
    }
}