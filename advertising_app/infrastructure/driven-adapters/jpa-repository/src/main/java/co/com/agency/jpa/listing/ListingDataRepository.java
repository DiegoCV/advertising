package co.com.agency.jpa.listing;

import co.com.agency.jpa.dealer.DealerData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.UUID;

public interface ListingDataRepository extends CrudRepository<ListingData, UUID>, QueryByExampleExecutor<ListingData> {

    List<ListingData> findByDealerAndState(DealerData dealer, String state);
    long countByDealerAndState(DealerData dealer, String state);
}
