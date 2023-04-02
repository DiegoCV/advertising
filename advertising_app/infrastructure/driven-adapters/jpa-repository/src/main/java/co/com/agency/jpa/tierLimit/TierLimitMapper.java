package co.com.agency.jpa.tierLimit;

import co.com.agency.jpa.helper.SimpleMapper;
import co.com.agency.model.tierlimit.TierLimit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TierLimitMapper extends SimpleMapper<TierLimit, TierLimitData> {
}
