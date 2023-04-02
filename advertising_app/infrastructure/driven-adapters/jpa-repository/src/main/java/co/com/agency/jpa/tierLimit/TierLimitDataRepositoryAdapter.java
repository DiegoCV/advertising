package co.com.agency.jpa.tierLimit;

import co.com.agency.jpa.helper.AdapterOperations;
import co.com.agency.jpa.listing.ListingDataRepository;
import co.com.agency.jpa.listing.ListingMapper;
import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.model.tierlimit.gateways.TierLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class TierLimitDataRepositoryAdapter extends AdapterOperations<TierLimit, TierLimitData, UUID,
        TierLimitDataRepository> implements TierLimitRepository {

    public TierLimitDataRepositoryAdapter(TierLimitDataRepository repository, TierLimitMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public TierLimit getLastTierLimitActive() {
        return this.toEntity(repository.findFirstActiveTrueTopByOrderByCreatedAtDesc());
    }

    public TierLimit save(TierLimit tierLimit){
        TierLimitData tierLimitData = this.toData(tierLimit);
        tierLimitData.setCreatedAt(new Date());

        return toEntity(repository.save(tierLimitData));
    }
}
