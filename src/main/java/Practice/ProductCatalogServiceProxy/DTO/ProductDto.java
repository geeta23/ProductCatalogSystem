package Practice.ProductCatalogServiceProxy.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
    private RatingDto ratingDto;
}
