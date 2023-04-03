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

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ResponseEntity<List<DealerResponse>> getAllDealer() {
        List<Dealer> dealerList = dealerUseCase.getAllDealer();
        return new ResponseEntity<List<DealerResponse>>(parse(dealerList), HttpStatus.OK);
    }

    private List<DealerResponse> parse(List<Dealer> dealerList){
        List<DealerResponse> dealerResponses = new ArrayList<>();
        for (Dealer dealer:dealerList) {
            dealerResponses.add(DealerResponse.builder().id(dealer.getId().toString()).name(dealer.getName()).build());
        }

        return dealerResponses;
    }
}
