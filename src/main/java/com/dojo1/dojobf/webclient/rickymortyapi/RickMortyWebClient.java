package com.dojo1.dojobf.webclient.rickymortyapi;

import com.dojo1.dojobf.exceptions.JokeNotFoundException;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.EpisodesDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.ResultsDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.RMCharactersDTO;
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


    public RMCharactersDTO getCharacterInfo(String num){
        log.info(">>> >> > running getCharacterInfo() method");

        String url ="character/";
        return createRequestToApi(url+num, RMCharactersDTO.class);
    }

    public List<ResultsDTO> getAllEpisodesList(){
        log.info(">>> >> > running getAllEpisodesList() method");
        /**
         * get pages count
         */
        int pages = getNumberOfPages();

        List<ResultsDTO> resultList = new ArrayList<>();

        /**
         * iterate through all pages with episodes
         * & add it to list and return
         */
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
        } catch (Exception e) {
            throw new JokeNotFoundException("list was not found"); //todo exception name
        }
        return info.getInfo().getPages();
    }

    private <T> T createRequestToApi(String url, Class<T> responseType) {
        return restTemplate.getForObject(RICK_MARTY_URL + url, responseType);
    }
}