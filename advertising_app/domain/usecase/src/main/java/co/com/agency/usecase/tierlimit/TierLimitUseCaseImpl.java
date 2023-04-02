package co.com.agency.usecase.tierlimit;

import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.model.tierlimit.gateways.TierLimitRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class TierLimitUseCaseImpl implements TierLimitUseCase{

    private final TierLimitRepository tierLimitRepository;

    @Override
    public TierLimit setTierLimit(TierLimit tierLimit) {
        tierLimit.setActive(true);
        return tierLimitRepository.save(tierLimit);
    }
}
