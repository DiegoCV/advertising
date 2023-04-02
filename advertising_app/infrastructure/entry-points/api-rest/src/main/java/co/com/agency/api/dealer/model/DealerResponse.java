package co.com.agency.api.dealer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class DealerResponse {
    @JsonProperty("id")
    @Schema(example = "104e5459-176b-4874-ad57-3dce462f7fd3")
    @Size(max = 40)
    private String id;

    @JsonProperty("name")
    @Schema(example = "Dealer name test")
    @Size(max = 255)
    private String name;
}
