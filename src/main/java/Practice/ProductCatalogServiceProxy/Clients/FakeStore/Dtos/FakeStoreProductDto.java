package Practice.ProductCatalogServiceProxy.Clients.FakeStore.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
    private FakeStoreRatingDto fakeStoreRatingDto;
}
