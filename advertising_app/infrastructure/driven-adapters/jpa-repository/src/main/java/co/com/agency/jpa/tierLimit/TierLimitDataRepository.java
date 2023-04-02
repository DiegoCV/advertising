package co.com.agency.jpa.tierLimit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface TierLimitDataRepository extends CrudRepository<TierLimitData, UUID>,
        QueryByExampleExecutor<TierLimitData> {

    TierLimitData findFirstActiveTrueTopByOrderByCreatedAtDesc();

}
