package com.dojo1.dojobf.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChuckApiWebClient {

    public static final String CHUCK_URL = "https://api.chucknorris.io/jokes/";
    private RestTemplate restTemplate = new RestTemplate();

}
