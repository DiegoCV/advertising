package co.com.agency.api.dealer;

import co.com.agency.api.dealer.model.DealerRequest;
import co.com.agency.api.dealer.model.DealerResponse;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.usecase.dealer.DealerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agency/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DealerController implements DealerOperations{

    private DealerUseCase dealerUseCase;

    @Override
    public ResponseEntity<DealerResponse> createDealer(DealerRequest body) {
        Dealer dealer = dealerUseCase.save(Dealer.builder().name(body.getName()).build());
        return new ResponseEntity<DealerResponse>(DealerResponse.builder().id(dealer.getId().toString())
                .name(dealer.getName()).build(), HttpStatus.OK);
    }
}
