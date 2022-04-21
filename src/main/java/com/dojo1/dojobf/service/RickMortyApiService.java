package com.dojo1.dojobf.service;

import com.dojo1.dojobf.model.RickMortySeasonDTO;
import com.dojo1.dojobf.model.RickMortySeasonsEpisodesDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.RickMortyWebClient;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.EpisodesResultsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RickMortyApiService {

    private final RickMortyWebClient rickMortyWebClient;

    public List<EpisodesResultsDTO> getAllEpisodesList(){
        return rickMortyWebClient.getAllEpisodesList();
    }


    public RickMortySeasonsEpisodesDTO getSeasonAndEpisodes(){

        List<EpisodesResultsDTO> list = getAllEpisodesList();
        int numOfLastSeason = getNumOfSeason(list.get(list.size()-1).getEpisode());

        List<List<EpisodesResultsDTO>> listOfSeasons = new ArrayList<>();
        List<RickMortySeasonDTO> listOfOneSeason = new ArrayList<>();

        for (int i = 1; i <= numOfLastSeason ; i++) {
            int n = i;
            List<EpisodesResultsDTO> l = new ArrayList<>();
            list.forEach( e-> { if (n == getNumOfSeason(e.getEpisode())) {
                l.add(e);
            }
            });
            listOfSeasons.add(l);
            RickMortySeasonDTO rmsDTO = RickMortySeasonDTO.builder().seasonNumber(i).episodesCount(l.size()).build();
            listOfOneSeason.add(rmsDTO);
        }
        return RickMortySeasonsEpisodesDTO.builder().listOfEpisodesInSeason(listOfOneSeason).build();
    }

    private int getNumOfSeason(String episode){
        episode = episode.substring(2,3);
        return Integer.parseInt(episode);
    }

}
