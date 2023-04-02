package co.com.agency.jpa.listing;

import co.com.agency.jpa.dealer.DealerData;
import co.com.agency.jpa.helper.SimpleMapper;
import co.com.agency.model.dealer.Dealer;
import co.com.agency.model.listing.Listing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ListingMapper extends SimpleMapper<Listing, ListingData> {

    default Dealer fromDealerData(final DealerData dealerData){
        if(dealerData == null){
            return null;
        }
        final Dealer dealer = Dealer.builder().id(dealerData.getId()).build();

        return dealer;
    }

    default DealerData fromDealer(final Dealer dealer){
        if(dealer == null){
            return null;
        }
        DealerData dealerData = new DealerData();
        dealerData.setId(dealer.getId());

        return dealerData;
    }

}
