package com.dojo1.dojobf.controller;

import com.dojo1.dojobf.exceptions.JokeNotFoundError;
import com.dojo1.dojobf.exceptions.JokeNotFoundException;
import com.dojo1.dojobf.model.ChuckJokeDto;
import com.dojo1.dojobf.service.ChuckApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "chuckapi/")
@RequiredArgsConstructor
public class ChuckApiController {

    private final ChuckApiService chuckApiService;

    /**
     * endpoint for random Chuck joke Object
     */
    @GetMapping(path = "/randomjoke")
    public ChuckJokeDto getRandomJoke() {
        return chuckApiService.getRandomJoke();
    }

    /**
     * endpoint for random Chuck joke Object of a given category as a param
     */
    @GetMapping(path = "/randomjoke/{category}")
    public ChuckJokeDto getRandomJoke(@PathVariable String category) {
        return chuckApiService.getRandomJoke(category);
    }

    @ExceptionHandler
    public ResponseEntity<JokeNotFoundError> handleException(JokeNotFoundException e) {
        JokeNotFoundError error = new JokeNotFoundError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}