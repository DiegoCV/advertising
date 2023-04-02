package co.com.agency.usecase.dealer;

import co.com.agency.common.DealerException;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.dealer.gateways.DealerRepository;
import lombok.RequiredArgsConstructor;

import static co.com.agency.common.CommonExceptionMessages.DEALER_NOT_EXISTS;

@RequiredArgsConstructor
public class DealerUseCaseImpl implements DealerUseCase {

    private final DealerRepository dealerRepository;

    @Override
    public Dealer save(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Override
    public void thrownExceptionIfDealerNotExist(Dealer dealer) {
        Dealer dealer1 = dealerRepository.findById(dealer.getId());
        if(dealer1 == null){
            throw new DealerException(DEALER_NOT_EXISTS);
        }
    }
}
