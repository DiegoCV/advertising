package co.com.agency.jpa.dealer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface DealerDataRepository extends CrudRepository<DealerData, UUID>, QueryByExampleExecutor<DealerData> {
}
