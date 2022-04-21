package com.dojo1.dojobf.controller;

import com.dojo1.dojobf.model.RickMortySeasonsEpisodesDTO;
import com.dojo1.dojobf.service.RickMortyApiService;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.EpisodesResultsDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "rickmortyapi/")
@RequiredArgsConstructor
public class RickMortyApiController {

    private final RickMortyApiService rmApiService;

//    @GetMapping(path = "/num")
//    public int getNum() {
//        return rmApiService.getPagesCount();
//    }

    @GetMapping(path = "/allepisodes")
    public List<EpisodesResultsDTO> getAllEpisodesList(){
        rmApiService.getSeasonAndEpisodes();
        return rmApiService.getAllEpisodesList();
    }

    @GetMapping(path = "/episodes")
    @CrossOrigin(origins = "http://localhost:4200")
    public RickMortySeasonsEpisodesDTO getSeasonAndEpisodes(){
        return rmApiService.getSeasonAndEpisodes();
    }


}
