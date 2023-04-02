package co.com.agency.jpa.dealer;

import co.com.agency.jpa.helper.AdapterOperations;
import co.com.agency.jpa.helper.SimpleMapper;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.dealer.gateways.DealerRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DealerDataRepositoryAdapter extends AdapterOperations<Dealer, DealerData, UUID, DealerDataRepository>
 implements DealerRepository {

    @Autowired
    DealerMapper dealerMapper;

    public DealerDataRepositoryAdapter(DealerDataRepository repository, DealerMapper mapper) {
        super(repository, mapper);
    }
}
