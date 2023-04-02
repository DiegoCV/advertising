package co.com.agency.model.dealer;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Dealer {
    private UUID id;
    private String name;
}
