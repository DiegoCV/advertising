package co.com.agency.api.dealer;

import co.com.agency.api.common.ErrorHandler;
import co.com.agency.api.dealer.model.DealerRequest;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.usecase.dealer.DealerUseCaseImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DealerControllerTest {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<DealerRequest> jsonDealerRequest;

    @InjectMocks
    @Spy
    private DealerController dealerController;

    @Mock
    private DealerUseCaseImpl dealerUseCase;

    @BeforeEach
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(dealerController)
                .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    void createDealer() throws Exception {
        DealerRequest dealerRequest = new DealerRequest("Name test");
        String json = jsonDealerRequest.write(dealerRequest).getJson();
        Dealer dealer = Dealer.builder().id(UUID.randomUUID()).name("Name test").build();
        Mockito.doReturn(dealer).when(dealerUseCase).save(any(Dealer.class));
        mvc.perform(
                        post("/agency/api/v1/create-dealer")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(status().isOk());
    }
}