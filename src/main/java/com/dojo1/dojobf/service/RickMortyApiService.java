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

    //FIXME this method is a bit against the single responsibility - make a method to get all seasons, make a method
    // to return episodes number from a single season

    /**
     * @return list of seasons with episodes number
     */
    public RMSeasonsListDTO getSeasonAndEpisodesDTO() {
        log.info(">>> >> running getSeasonAndEpisodesDTO() method");

        List<List<ResultsDTO>> listOfSeasonsWithListOfEpisodes = getListOfSeasonsWithListOfEpisodes();

        /**     create temp list of seasons and episodes    */ //TODO - double asterisk is for javadoc. Use /* */ or //
        List<RMSeasonNumEpisodeCountDTO> seasonAndEpisodesNumberList = new ArrayList<>(); // TODO instead of a comment, name the variable appropriately

        for (int i = 0; i < listOfSeasonsWithListOfEpisodes.size(); i++) {

            RMSeasonNumEpisodeCountDTO seasonAndEpisodesNumber = RMSeasonNumEpisodeCountDTO
                    .builder().seasonNumber(i + 1).episodesCount(listOfSeasonsWithListOfEpisodes.get(i).size()).build();

            seasonAndEpisodesNumberList.add(seasonAndEpisodesNumber);
        }
        return RMSeasonsListDTO.builder().listOfEpisodesInSeason(seasonAndEpisodesNumberList).build();
    }


    /**
     * @return season with list of all episodes with whole data
     */
    public RMSeasonsWithDetailsDTO getSeasonWithEpisodesDetails(int seasonNum) {
        log.info(">>> >> running getSeasonWithEpisodesDetails() method");

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


    private List<RMEpisodeDTO> getEpisodesOfSeason(int seasonNum) {

        List<List<ResultsDTO>> list = getListOfSeasonsWithListOfEpisodes();
        List<ResultsDTO> resultsDtoList = list.get(seasonNum - 1);

        List<RMEpisodeDTO> episodesListWithInfo = new ArrayList<>();

        for (int i = 0; i < resultsDtoList.size(); i++) {
        //FIXME instead of for i use a foreach loop - every loop you run get(i) 4 times.
            RMEpisodeDTO episode = RMEpisodeDTO.builder()
                    .name(resultsDtoList.get(i).getName())
                    .air_date(resultsDtoList.get(i).getAir_date())
                    .episode_characters(getListOfCharactersFromEpisode(resultsDtoList.get(i))).build();

            log.info("fetching < < < " + (i + 1) + "/" + (resultsDtoList.size()) + " name: " + resultsDtoList.get(i).getName());

            episodesListWithInfo.add(episode);
        }

        return episodesListWithInfo;
    }

    //FIXME instead of getting all episodes and attaching them to a season, get all seasons and then get episodes from them
    private List<List<ResultsDTO>> getListOfSeasonsWithListOfEpisodes() {

        List<ResultsDTO> listOfAllEpisodes = getAllEpisodesList();
        int numOfSeasons = getNumOfSeason(listOfAllEpisodes.get(listOfAllEpisodes.size() - 1).getEpisode());
        //FIXME instead of commenting, move to new method
        /** initialize new lists */
        List<List<ResultsDTO>> listOfSeasonsWithListOfEpisodes = new ArrayList<>();
        for (int i = 0; i < numOfSeasons; i++) {
            listOfSeasonsWithListOfEpisodes.add(new ArrayList<>());
        }

        /** iterate through all episodes & add episodes to proper season list*/
        for (int i = 0; i < listOfAllEpisodes.size(); i++) {

            ResultsDTO currentEpisode = listOfAllEpisodes.get(i);
            int numOfSeason = getNumOfSeason(currentEpisode.getEpisode());

            listOfSeasonsWithListOfEpisodes.get(numOfSeason - 1).add(currentEpisode);
        }
        return listOfSeasonsWithListOfEpisodes;
    }


    private List<RMCharacterDTO> getListOfCharactersFromEpisode(ResultsDTO episode) {
        List<RMCharacterDTO> list = new ArrayList<>();

        episode.getCharacters().forEach(c -> list.add(getCharacterInfo(c.substring(42)))); //TODO magic number

        return list;
    }


    private RMCharacterDTO getCharacterInfo(String num) {
        return rickMortyWebClient.getCharacterInfo(num);
    }


    private int getNumOfSeason(String episode) {
        episode = episode.substring(2, 3); //TODO magic number
        return Integer.parseInt(episode);
    }

}