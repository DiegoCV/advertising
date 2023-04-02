package co.com.agency.model.dealer.gateways;

import co.com.agency.model.dealer.Dealer;

import java.util.UUID;

public interface DealerRepository {
    Dealer save(Dealer dealer);
    Dealer findById(UUID id);
}
