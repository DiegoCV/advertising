package co.com.agency.api.listing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Validated
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListingRequest {

  @JsonProperty("vehicle")
  @Schema(example = "vehicle name")
  @Size(max = 255)
  private String vehicle;

  @JsonProperty("price")
  @Schema(example = "price")
  private float price;

  @JsonProperty("state")
  @Schema(example = "state")
  private String state;
}
