package co.com.agency.jpa.listing;

import co.com.agency.jpa.dealer.DealerMapper;
import co.com.agency.jpa.helper.AdapterOperations;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;
import co.com.agency.model.listing.gateways.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static co.com.agency.jpa.common.Constants.DRAFT;
import static co.com.agency.jpa.common.Constants.PUBLISHED;

@Repository
public class ListingDataRepositoryAdapter extends AdapterOperations<Listing, ListingData, UUID, ListingDataRepository>
 implements ListingRepository {

    @Autowired
    DealerMapper dealerMapper;

    public ListingDataRepositoryAdapter(ListingDataRepository repository, ListingMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Listing update(Listing listing) {
        ListingData listingData = this.toData(listing);
        listingData.setUpdateAt(new Date());

        return this.toEntity(repository.save(listingData));
    }

    @Override
    public List<Listing> getAllListingsByDealerAndState(Dealer dealer, String state) {
        return this.toList(repository.findByDealerAndState(dealerMapper.toData(dealer), state));
    }

    @Override
    public long cantListingPublishedByDealer(Dealer dealer) {
        return this.repository.countByDealerAndState(dealerMapper.toData(dealer), PUBLISHED);
    }

    @Override
    public Listing publishListing(Listing listing) {
        return updateState(listing, PUBLISHED);
    }

    @Override
    public Listing unPublishListing(Listing listing) {
        return updateState(listing, DRAFT);
    }

    private Listing updateState(Listing listing, String state){
        Optional<ListingData> byId = this.repository.findById(listing.getId());
        if(byId.isPresent()){
            ListingData ld = byId.get();
            ld.setState(state);
            listing = this.toEntity(this.repository.save(ld));
        }else {
            listing = null;
        }

        return listing;
    }
}
