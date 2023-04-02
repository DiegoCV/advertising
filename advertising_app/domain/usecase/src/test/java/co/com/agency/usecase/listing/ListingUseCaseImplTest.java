package co.com.agency.usecase.listing;

import co.com.agency.common.DealerException;
import co.com.agency.common.ListingException;
import co.com.agency.common.TierLimitException;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;
import co.com.agency.model.listing.gateways.ListingRepository;
import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.model.tierlimit.gateways.TierLimitRepository;
import co.com.agency.usecase.dealer.DealerUseCase;
import co.com.agency.usecase.dealer.DealerUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static co.com.agency.common.CommonExceptionMessages.LISTING_NOT_EXISTS;
import static co.com.agency.common.CommonExceptionMessages.TIER_LIMIT_NOT_DEFINED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ListingUseCaseImplTest {

    @InjectMocks
    @Spy
    ListingUseCaseImpl listingUseCase;

    @Mock
    protected DealerUseCaseImpl dealerUseCase;

    @Mock
    protected ListingRepository listingRepository;

    @Mock
    protected TierLimitRepository tierLimitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Listing listing = Listing.builder().dealer(Dealer.builder().build()).build();
        Mockito.doNothing().when(dealerUseCase).thrownExceptionIfDealerNotExist(any(Dealer.class));
        Mockito.doReturn(listing).when(listingRepository).save(any(Listing.class));
        assertNotNull(listingUseCase.save(listing));
    }

    @Test
    void updateExistingListing() {
        UUID randomUUID = UUID.randomUUID();
        Listing listing = Listing.builder().id(randomUUID).build();
        Mockito.doReturn(listing).when(listingRepository).findById(randomUUID);
        Mockito.doReturn(listing).when(listingRepository).update(any(Listing.class));
        assertNotNull(listingUseCase.update(listing));
    }

    @Test
    void updateNotExistingListing() {
        UUID randomUUID = UUID.randomUUID();
        Listing listing = Listing.builder().id(randomUUID).build();
        Mockito.doReturn(null).when(listingRepository).findById(randomUUID);
        ListingException thrown = Assertions.assertThrows(ListingException.class, () ->{
            listingUseCase.update(listing);
        });

        assertEquals(LISTING_NOT_EXISTS, thrown.getMessage());
    }

    @Test
    void getAllListingsByDealerAndState() {
        Dealer dealer = Dealer.builder().build();
        String state = "draft";
        List<Listing> listing = Arrays.asList(Listing.builder().build());
        Mockito.doReturn(listing).when(listingRepository).getAllListingsByDealerAndState(dealer, state);
        assertNotNull(listingUseCase.getAllListingsByDealerAndState(dealer, state));
    }

    @Test
    void publishListingSuccesfull() {
        Dealer dealer = Dealer.builder().build();
        TierLimit lastTierLimitActive = TierLimit.builder().tier(3).build();
        UUID randomUUID = UUID.randomUUID();
        Listing listing = Listing.builder().id(randomUUID).dealer(dealer).build();
        long cantListingPublishedByDealer = 2;
        Mockito.doReturn(listing).when(listingRepository).findById(randomUUID);
        Mockito.doReturn(cantListingPublishedByDealer).when(listingRepository)
                .cantListingPublishedByDealer(listing.getDealer());
        Mockito.doReturn(lastTierLimitActive).when(tierLimitRepository).getLastTierLimitActive();
        Mockito.doReturn(Listing.builder().build()).when(listingRepository).publishListing(any(Listing.class));
        assertNotNull(listingUseCase.publishListing(listing));
    }

    @Test
    void publishListingListingException() {
        UUID randomUUID = UUID.randomUUID();
        Listing listing = Listing.builder().id(randomUUID).build();
        Mockito.doReturn(null).when(listingRepository).findById(randomUUID);
        ListingException thrown = Assertions.assertThrows(ListingException.class, () ->{
            listingUseCase.publishListing(listing);
        });

        assertEquals(LISTING_NOT_EXISTS, thrown.getMessage());

    }

    @Test
    void publishListingTierLimitException() {
        Dealer dealer = Dealer.builder().build();
        UUID randomUUID = UUID.randomUUID();
        TierLimit lastTierLimitActive = TierLimit.builder().tier(3).build();
        Listing listing = Listing.builder().id(randomUUID).dealer(dealer).build();
        long cantListingPublishedByDealer = 3;
        Mockito.doReturn(listing).when(listingRepository).findById(randomUUID);
        Mockito.doReturn(lastTierLimitActive).when(tierLimitRepository).getLastTierLimitActive();
        Mockito.doReturn(cantListingPublishedByDealer).when(listingRepository)
                .cantListingPublishedByDealer(listing.getDealer());
        assertThrows(TierLimitException.class, () -> {
            listingUseCase.publishListing(listing);
        });

    }

    @Test
    void publishListingTierLimitExceptionMajor() {
        Dealer dealer = Dealer.builder().build();
        UUID randomUUID = UUID.randomUUID();
        TierLimit lastTierLimitActive = TierLimit.builder().tier(3).build();
        Listing listing = Listing.builder().id(randomUUID).dealer(dealer).build();
        long cantListingPublishedByDealer = 5;
        Mockito.doReturn(listing).when(listingRepository).findById(randomUUID);
        Mockito.doReturn(lastTierLimitActive).when(tierLimitRepository).getLastTierLimitActive();
        Mockito.doReturn(cantListingPublishedByDealer).when(listingRepository)
                .cantListingPublishedByDealer(listing.getDealer());
        assertThrows(TierLimitException.class, () -> {
            listingUseCase.publishListing(listing);
        });
    }

    @Test
    void publishListingTierLimitExceptionNotExist() {
        Dealer dealer = Dealer.builder().build();
        UUID randomUUID = UUID.randomUUID();
        Listing listing = Listing.builder().id(randomUUID).dealer(dealer).build();
        Mockito.doReturn(listing).when(listingRepository).findById(randomUUID);
        Mockito.doReturn(null).when(tierLimitRepository).getLastTierLimitActive();
        TierLimitException thrown = Assertions.assertThrows(TierLimitException.class, () ->{
            listingUseCase.publishListing(listing);
        });

        assertEquals(TIER_LIMIT_NOT_DEFINED, thrown.getMessage());

    }
    @Test
    void unPublishListingSuccessfull() {
        Listing listing = Listing.builder().build();
        Mockito.doReturn(listing).when(listingRepository).unPublishListing(any(Listing.class));
        assertNotNull(listingUseCase.unPublishListing(Listing.builder().build()));
    }

    @Test
    void unPublishListingException() {
        Mockito.doReturn(null).when(listingRepository).unPublishListing(any(Listing.class));
        ListingException thrown = Assertions.assertThrows(ListingException.class, () ->{
            listingUseCase.unPublishListing(Listing.builder().build());
        });

        assertEquals(LISTING_NOT_EXISTS, thrown.getMessage());
    }
}