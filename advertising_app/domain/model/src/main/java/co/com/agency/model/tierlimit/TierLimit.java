package co.com.agency.model.tierlimit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TierLimit {
    private boolean active;
    private int tier;
}
