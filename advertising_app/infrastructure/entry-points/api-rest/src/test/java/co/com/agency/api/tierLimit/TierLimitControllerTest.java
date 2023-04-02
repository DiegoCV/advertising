package co.com.agency.api.tierLimit;

import co.com.agency.api.common.ErrorHandler;
import co.com.agency.api.tierLimit.model.TierLimitRequest;
import co.com.agency.model.tierlimit.TierLimit;
import co.com.agency.usecase.tierlimit.TierLimitUseCaseImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TierLimitControllerTest {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<TierLimitRequest> jsonTierLimitRequest;

    @InjectMocks
    @Spy
    private TierLimitController tierLimitController;

    @Mock
    private TierLimitUseCaseImpl tierLimitUseCase;

    @BeforeEach
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(tierLimitController).setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    public void setTierLimit() throws Exception{

        TierLimitRequest tierLimitRequest = new TierLimitRequest(2);
        String json = jsonTierLimitRequest.write(tierLimitRequest).getJson();
        TierLimit tierLimit = TierLimit.builder().build();
        Mockito.doReturn(tierLimit).when(tierLimitUseCase).setTierLimit(any(TierLimit.class));
        mvc.perform(
                post("/agency/api/v1/set-tier-limit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void setTierLimitError() throws Exception{

        TierLimitRequest tierLimitRequest = new TierLimitRequest(2);
        String json = jsonTierLimitRequest.write(tierLimitRequest).getJson();
        Mockito.doReturn(null).when(tierLimitUseCase).setTierLimit(any(TierLimit.class));
        mvc.perform(
                        post("/agency/api/v1/set-tier-limit")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(status().isBadRequest());
    }


}