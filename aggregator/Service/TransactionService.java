package aggregator.Service;

import aggregator.Response;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {

    private final TransactionFetchService fetchService;

    public TransactionService(TransactionFetchService fetchService) {
        this.fetchService = fetchService;
    }

    @Cacheable(cacheNames = "data", key = "#account")
    public List<Response> getAggregatedTransactions(String account) {

        RestTemplate restTemplate = new RestTemplate();

        String url1 = "http://localhost:8888/transactions?account=" + account;
        String url2 = "http://localhost:8889/transactions?account=" + account;

        CompletableFuture<List<Response>> future1 = fetchService.getResponseList(url1, restTemplate);
        CompletableFuture<List<Response>> future2 = fetchService.getResponseList(url2, restTemplate);

        List<Response> responseList1 = future1.join();
        List<Response> responseList2 = future2.join();

        List<Response> result = new ArrayList<>();

        result.addAll(responseList1);
        result.addAll(responseList2);

        result.sort(Comparator.comparing(Response::getTimestamp).reversed());

        return result;
    }


}
