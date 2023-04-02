package co.com.agency.usecase.listing;

import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;

import java.util.List;

public interface ListingUseCase {
    Listing save(Listing listing);
    Listing update(Listing listing);
    List<Listing> getAllListingsByDealerAndState(Dealer dealer, String state);
    Listing publishListing(Listing listing);
    Listing unPublishListing(Listing listing);
}
