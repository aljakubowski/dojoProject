package com.dojo1.dojobf.controller;

import com.dojo1.dojobf.model.RMSeasonsListDTO;
import com.dojo1.dojobf.model.RMSeasonsWithDetailsDTO;
import com.dojo1.dojobf.service.RickMortyApiService;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.ResultsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rickmortyapi/")
@RequiredArgsConstructor
@Slf4j
public class RickMortyApiController {

    private final RickMortyApiService rmApiService;


    /**
     * @return endpoint with list of seasons and episodes number
     */
    @GetMapping(path = "/seasons")
    @CrossOrigin(origins = "http://localhost:4200")
    public RMSeasonsListDTO getSeasonAndEpisodes() {
        log.info(">>> receiving request on endpoint: /episodes");
        return rmApiService.getSeasonAndEpisodesDTO();
    }


    /**
     * @return endpoint with chosen season and list of episodes with list of characters
     */
    @GetMapping(path = "/seasons/{num}")
    @CrossOrigin(origins = "http://localhost:4200")
    public RMSeasonsWithDetailsDTO getSeasonAndEpisodesData(@PathVariable int num) {
        log.info(">>> receiving request on endpoint: /episodes");
        return rmApiService.getSeasonWithEpisodesDetails(num);
    }


    /**
     * raw list of all episodes
     */
    @GetMapping(path = "/episodesall")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ResultsDTO> getAllEpisodesList() {
        rmApiService.getSeasonAndEpisodesDTO();
        log.info(">>> receiving request on endpoint: /episodesall");
        return rmApiService.getAllEpisodesList();
    }
}