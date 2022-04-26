package com.dojo1.dojobf.webclient.rickymortyapi;

import com.dojo1.dojobf.exceptions.RMEpisodeError;
import com.dojo1.dojobf.exceptions.RMEpisodeException;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.EpisodesDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.ResultsDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.RMCharacterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class RickMortyWebClient {

    public static final String RICK_MARTY_URL = "https://rickandmortyapi.com/api/";
    private final RestTemplate restTemplate = new RestTemplate();


    public RMCharacterDTO getCharacterInfo(String num) {
        log.info(">>> >> > running getCharacterInfo() method");
        String url = "character/";
        return createRequestToApi(url + num, RMCharacterDTO.class);
    }


    public List<ResultsDTO> getAllEpisodesList() {
        log.info(">>> >> > running getAllEpisodesList() method");
        /**     get pages count     */
        int pages = getNumberOfPages();

        List<ResultsDTO> resultList = new ArrayList<>();
        /**
         * iterate through all pages with episodes
         * & add it to resultList and return          */
        for (int i = 1; i <= pages; i++) {
            EpisodesDTO ep;
            ep = createRequestToApi("episode?page=" + i, EpisodesDTO.class);
            List<ResultsDTO> pageList = new ArrayList<>(ep.getResults());
            resultList.addAll(pageList);
        }
        return resultList;
    }


    private int getNumberOfPages() {
        EpisodesDTO info;

        try {
            info = createRequestToApi("episode", EpisodesDTO.class);
        } catch (RMEpisodeException e){
            throw new RMEpisodeException(RMEpisodeError.EPISODE_NOT_FOUND_ERROR);
        }
        return info.getInfo().getPages();
    }


    private <T> T createRequestToApi(String url, Class<T> responseType) {
        return restTemplate.getForObject(RICK_MARTY_URL + url, responseType);
    }
}