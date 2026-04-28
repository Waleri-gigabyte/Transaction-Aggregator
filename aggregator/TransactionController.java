package aggregator;

import aggregator.Service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/aggregate")
    public List<Response> list(@RequestParam String account) {
        return service.getAggregatedTransactions(account);
    }
}
