package com.dojo1.dojobf.service;

import com.dojo1.dojobf.model.RMEpisodeDTO;
import com.dojo1.dojobf.model.RMSeasonNumEpisodeCountDTO;
import com.dojo1.dojobf.model.RMSeasonsListDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.RickMortyWebClient;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.RMCharactersDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.ResultsDTO;
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

    /**
     * @return list of all episodes with whole data
     */
    public List<ResultsDTO> getAllEpisodesList() {
        log.info(">>> >> running getAllEpisodesList() method");
        return rickMortyWebClient.getAllEpisodesList();
    }

    /**
     * @return list of all episodes of chosen season with characters data
     */
    public List<RMEpisodeDTO> getEpisodesOfSeason(int seasonNum) {
        log.info(">>> >> running getEpisodesOfSeason() method");

        List<ResultsDTO> listOfEpisodes = getEpisodesFromSeason(seasonNum);

        List<RMEpisodeDTO> resultList = new ArrayList<>();

        for (int i = 0; i < listOfEpisodes.size()-1; i++) {

            RMEpisodeDTO r = RMEpisodeDTO.builder()
                    .season(String.valueOf(seasonNum))
                    .name(listOfEpisodes.get(i).getName())
                    .air_date(listOfEpisodes.get(i).getAir_date())
                    .episode_characters(getListOfCharactersFromEpisode(listOfEpisodes.get(i))).build();
            log.info("fetching < < < " + (i+1) +"/" + (listOfEpisodes.size()-1) + " name: " +listOfEpisodes.get(i).getName());
            resultList.add(r);
        }

        return resultList;
    }

    private List<RMCharactersDTO> getListOfCharactersFromEpisode(ResultsDTO episode) {
        List<RMCharactersDTO> list = new ArrayList<>();

        episode.getCharacters().forEach(c -> {
            list.add(getCharacterInfo(c.substring(42)));
        });

        return list;
    }


    private RMCharactersDTO getCharacterInfo(String num) {
        return rickMortyWebClient.getCharacterInfo(num);
    }

    private List<ResultsDTO> getEpisodesFromSeason(int seasonNum) {
        List<List<ResultsDTO>> list = getListOfSeasonsWithListOfEpisodes();
        return list.get(seasonNum - 1);
    }

    private List<List<ResultsDTO>> getListOfSeasonsWithListOfEpisodes() {

        /** get all episodes */
        List<ResultsDTO> list = getAllEpisodesList();
        /** get num of episodes */
        int numOfLastSeason = getNumOfSeason(list.get(list.size() - 1).getEpisode());

        List<List<ResultsDTO>> dividedList = new ArrayList<>();

        /** iterate through all seasons & add episodes to proper seasons*/
        for (int i = 1; i <= numOfLastSeason; i++) {
            int seasonNum = i;
            List<ResultsDTO> listOfRes = new ArrayList<>();
            list.forEach(e -> {
                if (seasonNum == getNumOfSeason(e.getEpisode())) {
                    listOfRes.add(e);
                }
            });
            dividedList.add(listOfRes);
        }
        return dividedList;
    }

    /**
     * @return list of seasons with episodes numbed
     */
    public RMSeasonsListDTO getSeasonAndEpisodesDTO() {
        log.info(">>> >> running getSeasonAndEpisodesDTO() method");

        /** get all episodes */
        List<ResultsDTO> list = getAllEpisodesList();
        /** get num of episodes */
        int numOfLastSeason = getNumOfSeason(list.get(list.size() - 1).getEpisode());

        /** create temp list of seasons and episodes*/
        List<RMSeasonNumEpisodeCountDTO> listOfOneSeasonAndEpisodes = new ArrayList<>();
        /** iterate through all seasons & add episodes to proper seasons*/
        for (int i = 1; i <= numOfLastSeason; i++) {
            int seasonNum = i;
            List<ResultsDTO> listOfRes = new ArrayList<>();
            list.forEach(e -> {
                if (seasonNum == getNumOfSeason(e.getEpisode())) {
                    listOfRes.add(e);
                }
            });
            /** build RickMortySeasonWithEpisodesCountDTO - object with seasons and episodes number */
            RMSeasonNumEpisodeCountDTO rmsDTO = RMSeasonNumEpisodeCountDTO.builder().seasonNumber(i).episodesCount(listOfRes.size()).build();
            /** add RickMortySeasonDTO to temp list*/
            listOfOneSeasonAndEpisodes.add(rmsDTO);
        }

        /** create RickMortySeasonsListDTO - object with list of objects with seasonNum and episodes count*/
        return RMSeasonsListDTO.builder().listOfEpisodesInSeason(listOfOneSeasonAndEpisodes).build();
    }


    private int getNumOfSeason(String episode) {
        episode = episode.substring(2, 3);
        return Integer.parseInt(episode);
    }

}