package co.com.agency.api.listing.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.UUID;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class ListingResponse {
    private UUID id;
    private String vehicleId;
    private float price;
    private Date createdAt;
    private String state;
}
