package Practice.ProductCatalogServiceProxy.Clients.FakeStore.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreRatingDto {
    private double rate;
    private Long count;
}
