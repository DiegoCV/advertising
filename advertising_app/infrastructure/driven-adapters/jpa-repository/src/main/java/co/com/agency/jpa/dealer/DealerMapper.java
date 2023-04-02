package co.com.agency.jpa.dealer;

import co.com.agency.jpa.helper.SimpleMapper;
import co.com.agency.model.dealer.Dealer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealerMapper extends SimpleMapper<Dealer, DealerData> {
}
