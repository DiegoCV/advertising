package co.com.agency.model.listing;

import co.com.agency.model.dealer.Dealer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class Listing {
    private UUID id;
    private String vehicle;
    private float price;
    private Date createdAt;
    private String state;
    private Dealer dealer;
}
