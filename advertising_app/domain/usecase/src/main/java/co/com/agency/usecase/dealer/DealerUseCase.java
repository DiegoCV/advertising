package co.com.agency.usecase.dealer;

import co.com.agency.model.dealer.Dealer;

import java.util.List;

public interface DealerUseCase {
    Dealer save(Dealer dealer);
    void thrownExceptionIfDealerNotExist(Dealer dealer);
    List<Dealer> getAllDealer();
}
