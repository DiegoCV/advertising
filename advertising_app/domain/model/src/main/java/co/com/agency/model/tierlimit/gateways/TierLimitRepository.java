package co.com.agency.model.tierlimit.gateways;

import co.com.agency.model.tierlimit.TierLimit;

public interface TierLimitRepository {
    TierLimit getLastTierLimitActive();
    TierLimit save(TierLimit tierLimit);
}
