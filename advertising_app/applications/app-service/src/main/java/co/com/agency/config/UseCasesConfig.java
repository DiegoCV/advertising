package co.com.agency.config;

import co.com.agency.model.dealer.gateways.DealerRepository;
import co.com.agency.model.listing.gateways.ListingRepository;
import co.com.agency.model.tierlimit.gateways.TierLimitRepository;
import co.com.agency.usecase.dealer.DealerUseCase;
import co.com.agency.usecase.dealer.DealerUseCaseImpl;
import co.com.agency.usecase.listing.ListingUseCase;
import co.com.agency.usecase.listing.ListingUseCaseImpl;
import co.com.agency.usecase.tierlimit.TierLimitUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.agency.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    @Bean
    public DealerUseCase dealerUseCase(DealerRepository dealerRepository){
        return new DealerUseCaseImpl(dealerRepository);
    }

    @Bean
    public ListingUseCase listingUseCase(DealerUseCase dealerUseCase, ListingRepository listingRepository,
                                         TierLimitRepository tierLimitRepository){
        return new ListingUseCaseImpl(dealerUseCase, listingRepository, tierLimitRepository);
    }

    @Bean
    public TierLimitUseCaseImpl tierLimitUseCaseImpl(TierLimitRepository tierLimitRepository){
        return new TierLimitUseCaseImpl(tierLimitRepository);
    }
}
