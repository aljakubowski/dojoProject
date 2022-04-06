package com.dojo1.dojobf.controller;

import com.dojo1.dojobf.service.ChuckApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "chuckapi/")
public class ChuckApiController {

    private final ChuckApiService chuckApiService;


    @Autowired
    public ChuckApiController(ChuckApiService chuckApiService) {
        this.chuckApiService = chuckApiService;
    }

    @GetMapping(path = "/randomjoke")
    public String getRandomJoke() {
        return chuckApiService.getRandomJoke();
    }

}
