package co.com.agency.usecase.listing;

import co.com.agency.common.ListingException;
import co.com.agency.common.TierLimitException;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;
import co.com.agency.model.listing.gateways.ListingRepository;
import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.model.tierlimit.gateways.TierLimitRepository;
import co.com.agency.usecase.dealer.DealerUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

import static co.com.agency.common.CommonExceptionMessages.LISTING_NOT_EXISTS;
import static co.com.agency.common.CommonExceptionMessages.TIER_LIMIT_NOT_DEFINED;

@RequiredArgsConstructor
public class ListingUseCaseImpl implements ListingUseCase {

    private final DealerUseCase dealerUseCase;
    private final ListingRepository listingRepository;
    private final TierLimitRepository tierLimitRepository;

    @Override
    public Listing save(Listing listing) {
        dealerUseCase.thrownExceptionIfDealerNotExist(listing.getDealer());
        listing.setCreatedAt(new Date());
        return listingRepository.save(listing);
    }

    @Override
    public Listing update(Listing listing) {
        Listing listing1 = listingRepository.findById(listing.getId());
        if(listing1 == null){
            throw new ListingException(LISTING_NOT_EXISTS);
        }
        listing1.setPrice(listing.getPrice());
        listing1.setVehicle(listing.getVehicle());
        return listingRepository.update(listing1);
    }

    @Override
    public List<Listing> getAllListingsByDealerAndState(Dealer dealer, String state) {
        return listingRepository.getAllListingsByDealerAndState(dealer, state);
    }

    @Override
    public Listing publishListing(Listing listing) {
        Listing listing1 = listingRepository.findById(listing.getId());
        if(listing1 == null){
            throw new ListingException(LISTING_NOT_EXISTS);
        }
        long cantListingPublishedByDealer = listingRepository.cantListingPublishedByDealer(listing1.getDealer());
        TierLimit lastTierLimitActive = tierLimitRepository.getLastTierLimitActive();
        if(lastTierLimitActive == null){
            throw new TierLimitException(TIER_LIMIT_NOT_DEFINED);
        }
        long tierLimit = lastTierLimitActive.getTier();
        if(cantListingPublishedByDealer >= tierLimit){
            throw new TierLimitException();
        }

        return listingRepository.publishListing(listing);
    }

    @Override
    public Listing unPublishListing(Listing listing) {
        Listing listing1 = listingRepository.unPublishListing(listing);
        if(listing1 == null){
            throw new ListingException(LISTING_NOT_EXISTS);
        }

        return listing1;
    }
}
