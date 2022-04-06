package com.dojo1.dojobf.service;

import com.dojo1.dojobf.exceptions.JokeNotFoundException;
import com.dojo1.dojobf.model.ChuckJoke;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ChuckApiService {


    public String getRandomJoke(){
        String url = "https://api.chucknorris.io/jokes/random";
        return createRequest(url).toString();
    }


    private ChuckJoke createRequest(String url) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create((url))).build();

        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        log.info("JokeService => request: " + request);
        log.info("JokeService <<== response: " + response);


        ChuckJoke chuckJoke = new ChuckJoke();
        try {
            ObjectMapper mapper = new ObjectMapper();
            chuckJoke = mapper.readValue(response, ChuckJoke.class);
        } catch (Exception e) {
            throw new JokeNotFoundException("any jokes found for this request");
        }
        return chuckJoke;
    }
}