package co.com.agency.api.listing;

import co.com.agency.api.listing.model.ListingRequest;
import co.com.agency.api.listing.model.ListingResponse;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;
import co.com.agency.usecase.listing.ListingUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/agency/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ListingController implements ListingOperations {

    private ListingUseCase listingUseCase;

    @Override
    public ResponseEntity<ListingResponse> createListing(UUID dealerId, ListingRequest body) {
        Listing listing = listingUseCase.save(Listing.builder().dealer(Dealer.builder().id(dealerId).build())
                .price(body.getPrice()).vehicle(body.getVehicle()).build());

        return new ResponseEntity<ListingResponse>(ListingResponse.builder().id(listing.getId())
                .vehicleId(listing.getVehicle()).price(listing.getPrice()).createdAt(listing.getCreatedAt())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ListingResponse>> getAllListingsByDealerAndState(UUID dealerId, String status) {

        return new ResponseEntity<List<ListingResponse>>(parse(listingUseCase.getAllListingsByDealerAndState(
                Dealer.builder().id(dealerId).build(), status)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingResponse> publishListing(UUID listingId) {

        Listing request = Listing.builder().id(listingId).build();
        Listing listingPublished = listingUseCase.publishListing(request);

        return new ResponseEntity<ListingResponse>(ListingResponse.builder().id(listingPublished.getId())
                .vehicleId(listingPublished.getVehicle()).price(listingPublished.getPrice())
                .createdAt(listingPublished.getCreatedAt())
                .state(listingPublished.getState()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingResponse> unPublishListing(UUID listingId) {
        Listing request = Listing.builder().id(listingId).build();
        Listing listingPublished = listingUseCase.unPublishListing(request);

        return new ResponseEntity<ListingResponse>(ListingResponse.builder().id(listingPublished.getId())
                .vehicleId(listingPublished.getVehicle()).price(listingPublished.getPrice())
                .createdAt(listingPublished.getCreatedAt())
                .state(listingPublished.getState()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListingResponse> updateListing(UUID listingId, ListingRequest body) {
        Listing listing = listingUseCase.update(Listing.builder().id(listingId)
                .price(body.getPrice()).vehicle(body.getVehicle()).build());

        return new ResponseEntity<ListingResponse>(ListingResponse.builder().id(listing.getId())
                .vehicleId(listing.getVehicle()).price(listing.getPrice()).createdAt(listing.getCreatedAt())
                .state(listing.getState()).build(), HttpStatus.OK);
    }

    private List<ListingResponse> parse(List<Listing> listings){
        List<ListingResponse> listingResponses = new ArrayList<>();
        for (Listing listing: listings) {
            listingResponses.add(ListingResponse.builder().id(listing.getId())
                    .vehicleId(listing.getVehicle()).price(listing.getPrice()).createdAt(listing.getCreatedAt())
                    .state(listing.getState()).build());
        }

        return listingResponses;
    }
}
