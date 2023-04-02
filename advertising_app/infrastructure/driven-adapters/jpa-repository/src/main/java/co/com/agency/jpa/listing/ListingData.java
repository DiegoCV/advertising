package co.com.agency.jpa.listing;

import co.com.agency.jpa.dealer.DealerData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbl_listing")
public class ListingData{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updateAt;

    @Column(name = "vehicle", nullable = false)
    private String vehicle;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name="dealer_id")
    private DealerData dealer;

}
