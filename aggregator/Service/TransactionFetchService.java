package aggregator.Service;

import aggregator.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionFetchService {


    @Async
    public CompletableFuture <List<Response>> getResponseList(String url, RestTemplate restTemplate) {

        List<Response> responseList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            ResponseEntity<Response[]> response = null;
            int statusCode;

            try {
                response = restTemplate.getForEntity(url, Response[].class);
                statusCode = response.getStatusCode().value();
            } catch (RestClientResponseException e) {
                statusCode = e.getStatusCode().value();
            }

            if (statusCode == 200 && response != null) {
                Response[] responseArray = response.getBody();
                responseList = Arrays.asList(responseArray);
                break;
            }
        }

        return CompletableFuture.completedFuture(responseList);
    }
}
