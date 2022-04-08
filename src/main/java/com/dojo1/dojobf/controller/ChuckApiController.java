package com.dojo1.dojobf.controller;

import com.dojo1.dojobf.exceptions.JokeNotFoundError;
import com.dojo1.dojobf.exceptions.JokeNotFoundException;
import com.dojo1.dojobf.model.ChuckJoke;
import com.dojo1.dojobf.service.ChuckApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "chuckapi/")
public class ChuckApiController {

    private final ChuckApiService chuckApiService;

    @Autowired
    public ChuckApiController(ChuckApiService chuckApiService) {
        this.chuckApiService = chuckApiService;
    }

    // == endpoint for random Chuck joke ==
//    @GetMapping(path = "/randomjoke")
//    public String getRandomJoke() {
//        return chuckApiService.getRandomJoke();
//    }

    @CrossOrigin(origins = "https://chuck-api-app.herokuapp.com")
    @GetMapping(path = "/randomjoke")
    public ChuckJoke getRandomJoke() {
        return chuckApiService.getRandomJokeJson();
    }

    @ExceptionHandler
    public ResponseEntity<JokeNotFoundError> handleException(JokeNotFoundException e) {
        JokeNotFoundError error = new JokeNotFoundError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}