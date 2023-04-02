package co.com.agency.api.listing;

import co.com.agency.api.common.ErrorHandler;
import co.com.agency.api.dealer.model.DealerRequest;
import co.com.agency.api.listing.model.ListingRequest;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;
import co.com.agency.usecase.listing.ListingUseCaseImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ListingControllerTest {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<ListingRequest> jsonListingRequest;

    @InjectMocks
    @Spy
    private ListingController listingController;

    @Mock
    private ListingUseCaseImpl listingUseCase;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(listingController)
                .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    void createListing() throws Exception{
        ListingRequest listingRequest = new ListingRequest("MAZDA", 45, null);
        String json = jsonListingRequest.write(listingRequest).getJson();
        Listing listing = Listing.builder().id(UUID.randomUUID()).vehicle("MAZDA").price(45).createdAt(new Date())
                .build();
        Mockito.doReturn(listing).when(listingUseCase).save(any(Listing.class));
        String uuidDealer = UUID.randomUUID().toString();
        mvc.perform(
                        post("/agency/api/v1/create-listing/"+uuidDealer)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void getAllListingsByDealerAndState() throws Exception{
        Listing listing = Listing.builder().id(UUID.randomUUID()).vehicle("MAZDA").price(45).createdAt(new Date())
                .state("draft")
                .build();
        List<Listing> listingList = Arrays.asList(listing);
        Mockito.doReturn(listingList).when(listingUseCase).getAllListingsByDealerAndState(any(Dealer.class),
                anyString());
        String uuidDealer = UUID.randomUUID().toString();
        mvc.perform(
                        get("/agency/api/v1/get-all-listing-by-dealer-and-state/"+uuidDealer+"/draft")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void publishListing() throws Exception{
        Listing listing = Listing.builder().id(UUID.randomUUID()).vehicle("MAZDA").price(45).createdAt(new Date())
                .state("draft")
                .build();
        Mockito.doReturn(listing).when(listingUseCase).publishListing(any(Listing.class));
        String uuidListing = UUID.randomUUID().toString();
        mvc.perform(
                        post("/agency/api/v1/publish-listing/"+uuidListing)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void unPublishListing() throws Exception {
        Listing listing = Listing.builder().id(UUID.randomUUID()).vehicle("MAZDA").price(45).createdAt(new Date())
                .state("published")
                .build();
        Mockito.doReturn(listing).when(listingUseCase).unPublishListing(any(Listing.class));
        String uuidListing = UUID.randomUUID().toString();
        mvc.perform(
                        post("/agency/api/v1/un-publish-listing/"+uuidListing)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateListing() throws Exception{
        ListingRequest listingRequest = new ListingRequest("MAZDA", 45, "draft");
        String json = jsonListingRequest.write(listingRequest).getJson();
        Listing listing = Listing.builder().id(UUID.randomUUID()).vehicle("MAZDA").price(45).createdAt(new Date())
                .state("published")
                .build();
        Mockito.doReturn(listing).when(listingUseCase).update(any(Listing.class));
        String uuidListing = UUID.randomUUID().toString();
        mvc.perform(
                        post("/agency/api/v1/update-listing/"+uuidListing)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(status().isOk());
    }
}