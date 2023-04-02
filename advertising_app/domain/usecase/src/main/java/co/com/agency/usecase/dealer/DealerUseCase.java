package co.com.agency.usecase.dealer;

import co.com.agency.model.dealer.Dealer;

public interface DealerUseCase {
    Dealer save(Dealer dealer);
    void thrownExceptionIfDealerNotExist(Dealer dealer);
}
