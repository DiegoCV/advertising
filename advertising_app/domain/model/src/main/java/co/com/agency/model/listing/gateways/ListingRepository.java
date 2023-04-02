package co.com.agency.model.listing.gateways;

import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;

import java.util.List;
import java.util.UUID;

public interface ListingRepository {
    Listing findById(UUID id);
    Listing save(Listing listing);
    Listing update(Listing listing);
    List<Listing> getAllListingsByDealerAndState(Dealer dealer, String state);
    long cantListingPublishedByDealer(Dealer dealer);
    Listing publishListing(Listing listing);
    Listing unPublishListing(Listing listing);
}
