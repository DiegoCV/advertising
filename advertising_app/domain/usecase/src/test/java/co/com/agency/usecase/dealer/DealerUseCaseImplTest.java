package co.com.agency.usecase.dealer;

import co.com.agency.common.DealerException;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.dealer.gateways.DealerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.UUID;

import static co.com.agency.common.CommonExceptionMessages.DEALER_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DealerUseCaseImplTest {

    @InjectMocks
    @Spy
    DealerUseCaseImpl dealerUseCase;

    @Mock
    protected DealerRepository dealerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Dealer dealer = Dealer.builder().name("Test Dealer").build();
        Mockito.doReturn(dealer).when(dealerRepository).save(any(Dealer.class));
        assertNotNull(dealerUseCase.save(Dealer.builder().build()));
    }

    @Test
    void thrownExceptionIfDealerNotExist() {
        UUID uuid = UUID.randomUUID();
        Dealer dealer = Dealer.builder().id(uuid).build();
        Mockito.doReturn(null).when(dealerRepository).findById(uuid);
        DealerException thrown = Assertions.assertThrows(DealerException.class, () ->{
            dealerUseCase.thrownExceptionIfDealerNotExist(dealer);
        });

        assertEquals(DEALER_NOT_EXISTS, thrown.getMessage());
    }

    @Test
    void thrownExceptionIfDealerExist() {
        UUID uuid = UUID.randomUUID();
        Dealer dealer = Dealer.builder().id(uuid).build();
        Dealer dealerResponse = Dealer.builder().id(uuid).build();
        Mockito.doReturn(dealerResponse).when(dealerRepository).findById(uuid);
        assertDoesNotThrow(()->{
            dealerUseCase.thrownExceptionIfDealerNotExist(dealer);
        });
    }

}