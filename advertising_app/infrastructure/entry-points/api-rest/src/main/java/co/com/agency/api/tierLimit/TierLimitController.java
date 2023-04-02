package co.com.agency.api.tierLimit;

import co.com.agency.api.tierLimit.model.TierLimitRequest;
import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.usecase.tierlimit.TierLimitUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agency/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TierLimitController implements TierLimitOperations {

    private TierLimitUseCase tierLimitUseCase;

    @Override
    public ResponseEntity setTierLimit(TierLimitRequest body) {
        TierLimit tierLimit = tierLimitUseCase.setTierLimit(TierLimit.builder().tier(body.getTier()).build());
        if(tierLimit == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
