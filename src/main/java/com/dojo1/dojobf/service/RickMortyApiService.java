package com.dojo1.dojobf.service;

import com.dojo1.dojobf.model.RMEpisodeDTO;
import com.dojo1.dojobf.model.RMSeasonNumEpisodeCountDTO;
import com.dojo1.dojobf.model.RMSeasonsListDTO;
import com.dojo1.dojobf.model.RMSeasonsWithDetailsDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.RickMortyWebClient;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.RMCharacterDTO;
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
     * @return season with list of all episodes with whole data
     */
    public RMSeasonsWithDetailsDTO getAllSeasonsWithAllDetails(int seasonNum) {

        List<RMEpisodeDTO> episodesOfSeason = getEpisodesOfSeason(seasonNum);

        return RMSeasonsWithDetailsDTO.builder().season(String.valueOf(seasonNum)).episodes(episodesOfSeason).build();

    }

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

        List<ResultsDTO> resultsDtoList = getEpisodesFromSeason(seasonNum);

        List<RMEpisodeDTO> episodesListWithInfo = new ArrayList<>();

        for (int i = 0; i < resultsDtoList.size() - 1; i++) {

            RMEpisodeDTO episode = RMEpisodeDTO.builder()
                    .name(resultsDtoList.get(i).getName())
                    .air_date(resultsDtoList.get(i).getAir_date())
                    .episode_characters(getListOfCharactersFromEpisode(resultsDtoList.get(i))).build();

            log.info("fetching < < < " + (i + 1) + "/" + (resultsDtoList.size() - 1) + " name: " + resultsDtoList.get(i).getName());

            episodesListWithInfo.add(episode);
        }

        return episodesListWithInfo;
    }

    private List<RMCharacterDTO> getListOfCharactersFromEpisode(ResultsDTO episode) {
        List<RMCharacterDTO> list = new ArrayList<>();

        episode.getCharacters().forEach(c -> list.add(getCharacterInfo(c.substring(42))));

        return list;
    }


    private RMCharacterDTO getCharacterInfo(String num) {
        return rickMortyWebClient.getCharacterInfo(num);
    }


    private List<ResultsDTO> getEpisodesFromSeason(int seasonNum) {
        List<List<ResultsDTO>> list = getListOfSeasonsWithListOfEpisodes();
        return list.get(seasonNum - 1);
    }


    private List<List<ResultsDTO>> getListOfSeasonsWithListOfEpisodes() {

        List<ResultsDTO> listOfAllEpisodes = getAllEpisodesList();
        int numOfSeasons = getNumOfSeason(listOfAllEpisodes.get(listOfAllEpisodes.size() - 1).getEpisode());

        List<List<ResultsDTO>> listOfSeasonsWithListOfEpisodes = new ArrayList<>();

        /** iterate through all seasons & add episodes to proper season */
        for (int i = 1; i <= numOfSeasons; i++) {
            int seasonNum = i;
            List<ResultsDTO> episodesInCurrentSeason = new ArrayList<>();
            listOfAllEpisodes.forEach(e -> {
                if (seasonNum == getNumOfSeason(e.getEpisode())) {
                    episodesInCurrentSeason.add(e);
                }
            });
            listOfSeasonsWithListOfEpisodes.add(episodesInCurrentSeason);
        }
        return listOfSeasonsWithListOfEpisodes;
    }


    /**
     * @return list of seasons with episodes number
     */
    public RMSeasonsListDTO getSeasonAndEpisodesDTO() {
        log.info(">>> >> running getSeasonAndEpisodesDTO() method");

        List<ResultsDTO> listOfAllEpisodes = getAllEpisodesList();
        int numOfSeasons = getNumOfSeason(listOfAllEpisodes.get(listOfAllEpisodes.size() - 1).getEpisode());

        /**     create temp list of seasons and episodes    */
        List<RMSeasonNumEpisodeCountDTO> seasonAndEpisodesNumberList = new ArrayList<>();

        /** iterate through all seasons & add episodes to proper seasons    */
        for (int i = 1; i <= numOfSeasons; i++) {
            int seasonNum = i;
            List<ResultsDTO> episodesInCurrentSeason = new ArrayList<>();
            listOfAllEpisodes.forEach(e -> {
                if (seasonNum == getNumOfSeason(e.getEpisode())) {
                    episodesInCurrentSeason.add(e);
                }
            });

            RMSeasonNumEpisodeCountDTO seasonAndEpisodesNumber = RMSeasonNumEpisodeCountDTO
                    .builder().seasonNumber(i).episodesCount(episodesInCurrentSeason.size()).build();

            seasonAndEpisodesNumberList.add(seasonAndEpisodesNumber);
        }

        /** create RickMortySeasonsListDTO - object with list of objects with seasonNum and episodes count*/
        return RMSeasonsListDTO.builder().listOfEpisodesInSeason(seasonAndEpisodesNumberList).build();
    }


    private int getNumOfSeason(String episode) {
        episode = episode.substring(2, 3);
        return Integer.parseInt(episode);
    }

}