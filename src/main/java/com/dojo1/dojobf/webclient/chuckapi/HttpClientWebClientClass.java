//package com.dojo1.dojobf.webclient;
//
//import com.dojo1.dojobf.exceptions.JokeNotFoundException;
//import com.dojo1.dojobf.model.chuckjoke.ChuckJokeDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
///**
// * HttpCLient
// *  sync and async requests
// *  http 2 protocol
// *  does not convert json to java class itself
// */
//@Slf4j
//@Component
//public class HttpClientWebClientClass {
//
//    private final HttpClient client = HttpClient.newHttpClient();
//
//
//    private void createRequest(String url) {
//
////      HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create((url))).build();
//
//        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .join();
//
//        log.info("ChuckApiService => request: " + request);
//        log.info("ChuckApiService <<== response: " + response);
//
//
//        /**
//         * mapping to java class
//         */
//        ChuckJokeDto chuckJoke = new ChuckJokeDto();
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            chuckJoke = mapper.readValue(response, ChuckJokeDto.class);
//
//        } catch (Exception e) {
//
//        }
//
//        if (chuckJoke.toString() == null) {
//            throw new JokeNotFoundException("any jokes found for this request");
//        }
//
//    }
//}
