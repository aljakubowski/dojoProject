package com.dojo1.dojobf.webclient.rickymortyapi;

import com.dojo1.dojobf.exceptions.JokeNotFoundException;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.EpisodesDTO;
import com.dojo1.dojobf.webclient.rickymortyapi.dto.EpisodesResultsDTO;
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

    public List<EpisodesResultsDTO> getAllEpisodesList(){

        int pages = getNumberOfPages();
        log.info(">>> " + pages);
        List<EpisodesResultsDTO> resultList = new ArrayList<>();

        for (int i = 1; i <= pages; i++) {
            EpisodesDTO ep;
            log.info(">>> >> ?page=" + i);
            ep = createRequestToApi("episode?page=" + i, EpisodesDTO.class);
            log.info(">>> >> > " + ep);
            List<EpisodesResultsDTO> pageList = new ArrayList<>(ep.getResults());
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