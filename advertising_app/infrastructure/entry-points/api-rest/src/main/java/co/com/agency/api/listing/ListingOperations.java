package co.com.agency.api.listing;

import co.com.agency.api.listing.model.ListingRequest;
import co.com.agency.api.listing.model.ListingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;
import java.util.UUID;

public interface ListingOperations {
    @Operation(summary = "Create a new listing with status draft default", operationId = "createListing",
            description = "Create a new listing with status draft default", tags = "advertising")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successfully executed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ListingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong parameters or bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping(value = "/create-listing/{dealerId}", produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<ListingResponse> createListing(
            @Parameter(description = "Information related to dealer", required = true)
            @PathVariable("dealerId") UUID dealerId,
            @Parameter(description = "Information related to listing", required = true)
            @Validated({Default.class}) @RequestBody ListingRequest body
    );


    @Operation(summary = "To get the listings from a dealer and state", operationId = "getAllListingsByDealerAndState",
            description = "To get the listings from a dealer and state", tags = "advertising")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successfully executed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ListingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong parameters or bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @GetMapping(value = "/get-all-listing-by-dealer-and-state/{dealerId}/{state}", produces = {"application/json"})
    ResponseEntity<List<ListingResponse>> getAllListingsByDealerAndState(
            @Parameter(description = "Information related to dealer", required = true)
            @PathVariable("dealerId") UUID dealerId,
            @Parameter(description = "Information related to listing", required = true)
            @PathVariable("state") String state
    );

    @Operation(summary = "Change the status of a listing to publish having the tier limit condition",
            operationId = "publishListing",
            description = "Change the status of a listing to publish having the tier limit condition", tags = "advertising")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successfully executed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ListingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong parameters or bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PutMapping(value = "/publish-listing/{listingId}", produces = {"application/json"})
    ResponseEntity<ListingResponse> publishListing(
            @Parameter(description = "Information related to listing", required = true)
            @PathVariable("listingId") UUID listingId
    );

    @Operation(summary = "Change the status of a listing to draft", operationId = "unPublishListing",
            description = "Change the status of a listing to draft", tags = "advertising")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successfully executed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ListingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong parameters or bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PutMapping(value = "/un-publish-listing/{listingId}", produces = {"application/json"})
    ResponseEntity<ListingResponse> unPublishListing(
            @Parameter(description = "Information related to listing", required = true)
            @PathVariable("listingId") UUID listingId
    );

    @Operation(summary = "Update the information of a listing to draft", operationId = "updateListing",
            description = "Update the information of a listing to draft", tags = "advertising")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successfully executed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ListingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Wrong parameters or bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PutMapping(value = "/update-listing/{listingId}", produces = {"application/json"})
    ResponseEntity<ListingResponse> updateListing(
            @Parameter(description = "Information related to the id listing", required = true)
            @PathVariable("listingId") UUID listingId,
            @Parameter(description = "Information related to listing", required = true)
            @Validated({Default.class}) @RequestBody ListingRequest body
    );
}
