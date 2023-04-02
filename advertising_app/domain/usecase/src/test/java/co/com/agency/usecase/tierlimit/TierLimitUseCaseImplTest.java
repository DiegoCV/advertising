package co.com.agency.usecase.tierlimit;

import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.model.tierlimit.gateways.TierLimitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class TierLimitUseCaseImplTest {

    @InjectMocks
    @Spy
    TierLimitUseCaseImpl tierLimitUseCase;

    @Mock
    protected TierLimitRepository tierLimitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setTierLimit() {
        TierLimit tierLimit = TierLimit.builder().build();
        Mockito.doReturn(tierLimit).when(tierLimitRepository).save(any(TierLimit.class));
        assertNotNull(this.tierLimitUseCase.setTierLimit(TierLimit.builder().build()));
    }
}