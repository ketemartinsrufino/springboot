package cade.api;

import cade.application.removespaces.DicionarioAberto;
import cade.application.removespaces.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;


/**
 * Created by cade on 02/08/17.
 */
public class DicionarioAbertoAPI {

    private static final Logger log = LoggerFactory.getLogger(DicionarioAbertoAPI.class);

    public static int errorsQte = 0;
    public static  int successsQte = 0;

    final String URL = "http://dicionario-aberto.net/search-json/";
    RestTemplate restTemplate = new RestTemplate();

    public Entry getWord(String word) {
        String request = "";
        DicionarioAberto quote = null;
        Entry entry = null;

        log.info(request);

        try {

            String url = URL + UriUtils.encode(word, "UTF-8");
            UriComponents uriComp = UriComponentsBuilder.fromUriString(url).build(true);

            quote = restTemplate.getForObject(uriComp.toUri(), DicionarioAberto.class);

            entry = quote.getEntry();
            if(entry == null) {
                //pegando a primeira da lista só pra ver como fica
                entry = quote.getSuperEntry().get(0);
            }
            log.info(entry.getId());
            successsQte++;
        } catch (final HttpClientErrorException e) {
//            log.error("Error on word [" + word + "]: "+ e.getStatusCode());
//            System.out.println(e.getResponseBodyAsString());
            errorsQte++;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return entry;
    }
}
