package co.com.agency.api.tierLimit;

import co.com.agency.api.tierLimit.model.TierLimitRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.groups.Default;

public interface TierLimitOperations {
    @Operation(summary = "Set the tier limit to publish listing", operationId = "setTierLimit",
            description = "Set the tier limit to publish listing", tags = "advertising")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successfully executed"),
            @ApiResponse(responseCode = "400", description = "Wrong parameters or bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping(value = "/set-tier-limit", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity setTierLimit(
            @Parameter(description = "Information related to TierLimit", required = true)
            @Validated({Default.class}) @RequestBody TierLimitRequest body
    );
}

