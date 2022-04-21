package com.dojo1.dojobf.controller.chuckjoke;

import com.dojo1.dojobf.service.chuckjoke.ChuckApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "chuckapi/")
@RequiredArgsConstructor
public class ChuckApiController {

    private final ChuckApiService chuckApiService;

    /**
     * endpoint for random Chuck joke Object
     */
//    @GetMapping(path = "/randomjoke")
//    public ChuckJokeDto getRandomJoke() {
//        return chuckApiService.getRandomJoke();
//    }


//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping(path = "/randomjoke")
//    public ChuckJoke getRandomJoke() {
//        return chuckApiService.getRandomJokeJson();

    /**
     * endpoint for random Chuck joke Object of a given category as a param
     */
//    @GetMapping(path = "/randomjoke/{category}")
//    public ChuckJokeDto getRandomJoke(@PathVariable String category) {
//        return chuckApiService.getRandomJoke(category);
//>>>>>>> web-client
//    }

//    @ExceptionHandler
//    public ResponseEntity<JokeNotFoundError> handleException(JokeNotFoundException e) {
//        JokeNotFoundError error = new JokeNotFoundError();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(e.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
}